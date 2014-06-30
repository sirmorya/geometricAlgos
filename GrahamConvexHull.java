package geometricAlgos;

import java.util.Arrays;
import java.util.Stack;


class GrahamPoint extends Point implements Comparable<GrahamPoint>{
	
	
	public GrahamPoint(int x, int y) {
		super(x, y);
		this.x = x;
		this.y = y;
	}
	
	
	
	@Override
	public int compareTo(GrahamPoint o) {
		GrahamConvexHull gcH = new GrahamConvexHull();
		int orientation = gcH.orientation(gcH.p0,this,o);
		// If the points are collinear then choose the nearest point closer
		if(orientation == 0)
			return gcH.dist(gcH.p0, o) >= gcH.dist(gcH.p0, this) ? -1 : 1; 
		
		return orientation;
	}
	
}

public class GrahamConvexHull {
	
	int dist(Point p1,Point p2){
		return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
	}
	

	
	static Point p0 = null;
	
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
	
	
	GrahamPoint nextToTop(Stack<GrahamPoint> stack){
		GrahamPoint top = stack.peek();
		stack.pop();
		GrahamPoint nexttoTop = stack.peek();
		stack.push(top);
		return nexttoTop;
	}
	
	void grahamConvexHull(GrahamPoint[] polygon){
		
		int n= polygon.length, index = 0;
		//Storing the first point as the origin
		GrahamPoint min = polygon[0];
		
		//Find the point having the minimum y co-ordinate  
		for( int i = 1; i < n; i++){
			if(polygon[i].y < min.y || (polygon[i].y == min.y && polygon[i].x < min.x)){
				min = polygon[i];
				index = i;
			}
				
			
		}
		
		GrahamPoint tmp = polygon[0];
		polygon[0] = polygon[index];
		polygon[index] = tmp;
		
		p0 = polygon[0];
		
		//Sort the remaining points based on the co-ordinate angle the vertex makes at the origin
		Arrays.sort(polygon, 1, polygon.length - 1);
		
	
		
		Stack<GrahamPoint> stack = new Stack<GrahamPoint>();
		stack.add(polygon[0]);
		stack.add(polygon[1]);
		stack.add(polygon[2]);
		
		for(int i = 3; i < n; i++){
			
			while(orientation(nextToTop(stack), stack.peek(), polygon[i]) != -1)
				stack.pop();
			stack.push(polygon[i]);
		}
		
		while(!stack.isEmpty()){
			Point point = stack.pop();
			System.out.println("(" + point.x+", "+point.y);
		}
		
	}
	
	public static void main(String[] args) {
		
		GrahamPoint polygon[] = {new GrahamPoint(0, 3), new GrahamPoint(1, 1), new GrahamPoint(2, 2), new GrahamPoint(4, 4),
                new GrahamPoint(0, 0), new GrahamPoint(1, 2), new GrahamPoint(3, 1), new GrahamPoint(3, 3)};
		new GrahamConvexHull().grahamConvexHull(polygon);
		
	}
	
	
}
