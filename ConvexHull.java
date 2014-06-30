package geometricAlgos;

/**
 * This class is used to generate a convex hull; i.e. the smallest convex polygon which includes all the points
 * @author ankitsirmorya
 *
 */
public class ConvexHull {
	
	

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
	 * This method prints the convex hull which is the smallest polygon which
	 * surrounds all the points.
	 * 
	 * This method uses the Jarvis's Algorithm : Start from the leftmost point(left most point) and start wrapping up in counter clockwise direction.
	 * From any point 'p' in the polygon the next point 'q' is searched for which the slope of line p -> q is less than all the other points 
	 *            
	 *               
	 *       s1->\    s2 -> |        s3 ->  /
	 *            \         |              /
	 *             
	 *            So, in there kind of slopes we look for the next point q such that the slope of line p->q is 's1'.
	 *            Such, an implementation will occupy all the points
	 *            
 	 * @param polygon
	 */
	void convexHull(Point[] polygon){
		
		int n = polygon.length;
		int l = polygon[0].x;
		//finding the leftmost point
		for(int i = 1; i < n; i++)
			if(polygon[i].x < l)
				l = polygon[i].x;
		
		int []next = new int[n];
		//Storing the default values
		for(int i = 0; i < n; i++)
			next[i] = -1;
		
		int p = l, q;
		do{
			//Next point to be considered
			q = (p + 1) % n;
			//Checking each of the points to generate a convex hull
			for(int i = 0; i < n; i++)
				if(orientation(polygon[p], polygon[i], polygon[q]) == -1)
					q = i;
			
			next[p] = q;//Add q as a next result of p
			p = q;//setting the value for next iteration
		}while(p != l);
		
		for(int i = 0; i < n; i++)
			if(next[i] != -1)
				System.out.println("("+ polygon[i].x+", "+polygon[i].y+" )");
	}

	
	public static void main(String[] args) {
		
		 Point points[] = {new Point(0, 3), new Point(2, 2), new Point(1, 1), new Point(2, 1),
                 new Point(3, 0), new Point(0, 0), new Point(3, 3)};
		 new ConvexHull().convexHull(points);
	}
}









