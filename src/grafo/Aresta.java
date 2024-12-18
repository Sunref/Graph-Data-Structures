package grafo;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;

/**
 * Uma aresta de um grafo.
 * 
 * @author Prof. Dr. David Buzatto
 */
public class Aresta {
    
    public Vertice origem;
    public Vertice destino;

    public Aresta( Vertice origem, Vertice destino ) {
        this.origem = origem;
        this.destino = destino;
    }
    
    public void draw( EngineFrame e ) {
        e.drawLine( origem.pos, destino.pos, EngineFrame.BLACK );
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Aresta aresta = (Aresta) obj;
        return (origem.equals(aresta.origem) && destino.equals(aresta.destino))
                || (origem.equals(aresta.destino) && destino.equals(aresta.origem));
    }

    @Override
    public int hashCode() {
        return origem.hashCode() + destino.hashCode();
    }
    
}
