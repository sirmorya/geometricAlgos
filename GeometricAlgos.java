package geometricAlgos;


class Point{
	int x, y;
	
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
}


/**
* This class is used to check whether two lines intersect.
* @author ankit
*
*/
public class GeometricAlgos {

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




public static void main(String[] args) {
	
	System.out.println(new GeometricAlgos().doIntersect(new Point(10, 0), new Point(0, 10), new Point(0, 0), new Point(10, 10)));
}

}
