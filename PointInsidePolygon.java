package geometricAlgos;

public class PointInsidePolygon {
	
	/**
	 * This method is used to determine the orientation of points p -> q and q -> r
	 *              r
	 *             / -> s1(slope of line q -> r)
	 *            q
	 *            /  -> s2(slope of line p -> q)
	 *           p
	 *           If s1 < s2 , then orientation is clockwise
	 *           else the orientation is anti-clockwise
	 * @param p
	 * @param q
	 * @param r
	 * @return
	 */
	int orientation(Point p, Point q, Point r){
		
		int val = (q.y - p.y) * (r.x - q.x)
				- (q.x - p.x) * (r.y - q.y);
		
		if( val == 0) return 0;// Collinear
		
		return(val > 0 ) ? 1 : -1;// Clockwise or Anti-Clockwise
	}


	/**
	 * This method checks if q lies on the line p -> r
	 * 
	 * 					.________._________.
	 * 					p		q		   r
	 * @param p
	 * @param q
	 * @param r
	 * @return
	 */
	boolean isSegment(Point p, Point q, Point r){
		return ( q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
				  q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y));
				
	}


	/**
	 * This method checks whether two lines intersect.
	 * 
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @return
	 */
	boolean doIntersect(Point p1, Point p2, Point p3, Point p4){

		int o1 = orientation(p1, p2, p3);
		int o2 = orientation(p1, p2, p4);
		int o3 = orientation(p3, p4, p1);
		int o4 = orientation(p3, p4, p2);
		
		//General Case
		if(o1 != o2 && o3 != o4)
			return true;
		
		//Special Cases
		// If p3 lies in the line segment p1 --> p2
		if( o1 == 0 && isSegment(p1, p3, p2)) return true;
		if( o2 == 0 && isSegment(p1, p4, p2)) return true;
		if( o3 == 0 && isSegment(p3, p4, p1)) return true;
		if( o4 == 0 && isSegment(p3, p4, p2)) return true;
		
		return false;
	}

	
	/**
	 * It returns true if point "p" lies inside a polygon.
	 * Draw a horizontal line from the point uptil infinity and if it cuts the polyon odd number of times then the point lies inside.
	 * @param polygon
	 * @param p
	 * @return
	 */
	boolean isInside(Point[] polygon, Point p){
		
		int n = polygon.length;
		
		//Polygon must be of atleast 3 sides
		if( n < 3) return false;
		
		//Drawing horizontal line from the point "p" to infinity
		Point extreme = new Point(10000, p.y);
		
		int count = 0, i = 0, next;
		
		do{
			next = (i +1) % n;
			if(doIntersect(polygon[i], polygon[next], p, extreme)){
				
				if(orientation(polygon[i], polygon[next], p) == 0)
					return isSegment(polygon[i], p, polygon[next]);
				count++;
			}
			i = next;
		}while(i != 0);
		return (count % 2 == 1) ? true: false;
	}
	public static void main(String[] args) {
		Point polygon1[] = {new Point(0, 0), new Point(10, 0), new Point(10, 10), new Point(0, 10)};
		System.out.println(new PointInsidePolygon().isInside(polygon1, new Point(5, 5)));
	}
}



















