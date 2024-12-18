package grafo;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Um grafo implementado usando uma tabela de símbolos.
 * 
 * @author Prof. Dr. David Buzatto
 */
public class Grafo {

  public Map<Vertice, List<Aresta>> st;
  public Map<Integer, Vertice> vertices;

  public Grafo() {
    st = new TreeMap<>();
    vertices = new TreeMap<>();
  }

  public Vertice addVertice(double x, double y) {
    Vertice v = new Vertice(vertices.size(), x, y);
    vertices.put(v.id, v);
    return v;
  }

  public void addAresta(int origem, int destino) {
    Vertice vo = vertices.get(origem);
    Vertice vd = vertices.get(destino);
    if (!st.containsKey(vo)) {
      st.put(vo, new ArrayList<>());
    }
    if (!st.containsKey(vd)) {
      st.put(vd, new ArrayList<>());
    }
    st.get(vo).add(0, new Aresta(vo, vd));
    st.get(vd).add(0, new Aresta(vd, vo));
  }

  public List<Aresta> adjacentes(int origem) {
    return st.getOrDefault(vertices.get(origem), new ArrayList<>());
  }

  public int getQuantidadeVertices() {
    return vertices.size();
  }

  public void draw(EngineFrame e) {

    for (Map.Entry<Vertice, List<Aresta>> entry : st.entrySet()) {
      for (Aresta a : entry.getValue()) {
        a.draw(e);
      }
    }

    for (Map.Entry<Integer, Vertice> entry : vertices.entrySet()) {
      entry.getValue().draw(e);
    }

  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();

    for (Map.Entry<Vertice, List<Aresta>> entry : st.entrySet()) {
      sb.append(entry.getKey()).append(" -> ");
      boolean primeiro = true;
      for (Aresta a : entry.getValue()) {
        if (primeiro) {
          primeiro = false;
        } else {
          sb.append(", ");
        }
        sb.append(a.destino.id);
      }
      sb.append("\n");
    }

    return sb.toString().trim();

  }

  // INFO A lista retornada pode ser usada para desenhar as setas da arvore resultante,
  // pois cada aresta armazena seu destinho e origem
  public List<Aresta> DFS(Vertice origem) {

    Set<Vertice> visitados = new HashSet<>();
    List<Aresta> edgeList = new ArrayList<>();
    Stack<Vertice> stack = new Stack<>();

    stack.push(origem);

    while (!stack.isEmpty()) {
      Vertice atual = stack.pop();

      if (!visitados.contains(atual)) {
        visitados.add(atual);

        for (Aresta aresta : st.get(atual)) {
           if (!visitados.contains(aresta.destino)) {
                if (!edgeList.contains(aresta)) {
                    edgeList.add(aresta);
                }
                
                if (!stack.contains(aresta.destino)) {
                    stack.push(aresta.destino);
                }
                
            }
        }
      }
    }

    return edgeList;
  }

  public List<Aresta> BFS(Vertice origem) {

    Set<Vertice> visitados = new HashSet<>();
    List<Aresta> edgeList = new ArrayList<>();
    Queue<Vertice> fila = new LinkedList<>();
    fila.add(origem);

    while (!fila.isEmpty()) {
      Vertice atual = fila.poll();

      if (!visitados.contains(atual)) {
        visitados.add(atual);

        for (Aresta aresta : st.get(atual)) {
          if (!visitados.contains(aresta.destino)) {
                if (!edgeList.contains(aresta)) {
                    edgeList.add(aresta);
                }
                
                if (!fila.contains(aresta.destino)) {
                    fila.add(aresta.destino);
                }
            }
        }
      }
    }

    return edgeList;
  }
}
