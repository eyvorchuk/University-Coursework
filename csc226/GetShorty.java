// Kattis problem "Get Shorty" 3.4
// Type: SSSP 
// Comment: Bell-Ford, convert factors to sums using logs

import java.util.*;

class Edge {
   int v, w; // endpoints
   double d; // distance
   Edge( int vv, int ww, double dd ) { v=vv; w=ww; d=dd; }
}

class GetShorty {
   
    public static void main( String[] args ) {
       Scanner in = new Scanner( System.in );
       while (true) {
          int N  = in.nextInt();  // Verts 1 <=  N <= 10,000
          int M2 = in.nextInt();  // Edges 1 <= M2 <= 15,000
          Edge[] E = new Edge[2*M2];  // Array of all edges
          double[] dist = new double[N];
          if (N==0 && M2==0) return;
          int M = 0; // 2*M2 after loop
          for (int m=0; m<M2; ++m) {
             int v = in.nextInt(), w = in.nextInt();
             double d = -Math.log( in.nextDouble() );
             E[M++] = new Edge( v, w, d );
             E[M++] = new Edge( w, v, d );
          }
          // Try naive Bellman-Ford, checking "changed".
          Arrays.fill( dist, Double.MAX_VALUE );
          dist[0] = 0.0;
          boolean changed = true;
          while (changed) {
             changed = false;
             for (int i=0; i<M; ++i) 
                if (dist[E[i].v] != Double.MAX_VALUE)
                if (dist[E[i].v]+E[i].d < dist[E[i].w]) { 
                   changed = true;
                   dist[E[i].w] = dist[E[i].v]+E[i].d;
                } 
          } 
          System.out.printf( "%.4f\n", Math.exp( -dist[N-1] ) );
       }
   }
}
