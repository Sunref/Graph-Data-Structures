import java.util.List;
import java.util.Map.Entry;

import br.com.davidbuzatto.jsge.collision.CollisionUtils;
import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.math.Vector2;
import grafo.Grafo;
import grafo.Vertice;
import grafo.Aresta;
import utils.Utils;

/**
 * Simulador de Busca em Largura.
 * 
 * @author Prof. Dr. David Buzatto
 */
public class SimuladorBuscaLargura extends EngineFrame {

  private Grafo grafo;

  public SimuladorBuscaLargura() {
    super(650, 500, "Busca em Largura", 60, true);
  }

  @Override
  public void create() {
    grafo = Utils.criarGrafoTeste();
    System.out.println(grafo);
    setDefaultFontSize(20);
    setDefaultStrokeLineWidth(2);
    setDefaultStrokeEndCap(STROKE_CAP_ROUND);
    // TODO Usar essa lista para desenhas as setas
    List<Aresta> arvoreResultante;
  }

  @Override
  public void update(double delta) {

    Vector2 mousePos = getMousePositionPoint();
    if (isMouseButtonPressed(MOUSE_BUTTON_LEFT)) {
      System.out.println("M1");
      for (Entry<Integer, Vertice> entry : grafo.vertices.entrySet()) {
        if (CollisionUtils.checkCollisionPointCircle(mousePos, entry.getValue().pos, 30)) {
          System.out.println(entry.getValue().id);
          grafo.bfs(entry.getValue());
        }
      }
    }
    if (isMouseButtonPressed(MOUSE_BUTTON_RIGHT)) {
      System.out.println("M2");
      for (Entry<Integer, Vertice> entry : grafo.vertices.entrySet()) {
        if (CollisionUtils.checkCollisionPointCircle(mousePos, entry.getValue().pos, 30)) {
          System.out.println(entry.getValue().id);
          grafo.dfs(entry.getValue());
        }
      }
    }
  }

  @Override
  public void draw() {
    clearBackground(WHITE);
    drawText("Clique para escolher a fonte e executar o algoritmo.", 10, 10, BLACK);
    grafo.draw(this);
  }

  public static void main(String[] args) {
    new SimuladorBuscaLargura();
  }

}
