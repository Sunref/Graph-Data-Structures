import java.util.List;
import java.util.Map.Entry;

import br.com.davidbuzatto.jsge.collision.CollisionUtils;
import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.math.Vector2;
import grafo.Grafo;
import grafo.Vertice;
import grafo.Aresta;
import java.awt.Color;
import utils.Utils;

/**
 * Simulador de Busca em Largura.
 * 
 * @author Prof. Dr. David Buzatto
 */
public class SimuladorBusca extends EngineFrame {

  private Grafo grafo;
  private List<Aresta> arvoreResultante;


public void desenharSeta(Vector2 origem, Vector2 destino, int larguraPonta, int alturaPonta, int raioGrafo, Color corSeta) {
    // Calcula o ângulo da seta
    double angulo = Math.atan2(destino.y - origem.y, destino.x - origem.x);

    // Ajusta o ponto inicial da linha para começar na borda do grafo
    int xOrigemAjustado = (int) (origem.x + raioGrafo * Math.cos(angulo));
    int yOrigemAjustado = (int) (origem.y + raioGrafo * Math.sin(angulo));

    // Ajusta o ponto final da linha para terminar antes da borda do grafo no destino
    int xDestinoAjustado = (int) (destino.x - raioGrafo * Math.cos(angulo));
    int yDestinoAjustado = (int) (destino.y - raioGrafo * Math.sin(angulo));

    // Desenha a linha principal da seta
    drawLine(new Vector2(xOrigemAjustado, yOrigemAjustado), new Vector2(xDestinoAjustado, yDestinoAjustado), corSeta);

    // Calcula os pontos da ponta da seta
    int xPonta1 = (int) (xDestinoAjustado - larguraPonta * Math.cos(angulo - Math.PI / 6));
    int yPonta1 = (int) (yDestinoAjustado - larguraPonta * Math.sin(angulo - Math.PI / 6));
    int xPonta2 = (int) (xDestinoAjustado - larguraPonta * Math.cos(angulo + Math.PI / 6));
    int yPonta2 = (int) (yDestinoAjustado - larguraPonta * Math.sin(angulo + Math.PI / 6));

    // Desenha as linhas da ponta da seta
    drawLine(new Vector2(xDestinoAjustado, yDestinoAjustado), new Vector2(xPonta1, yPonta1), corSeta);
    drawLine(new Vector2(xDestinoAjustado, yDestinoAjustado), new Vector2(xPonta2, yPonta2), corSeta);
}

  
  
  public SimuladorBusca() {
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
    
  }

  @Override
  public void update(double delta) {

    Vector2 mousePos = getMousePositionPoint();
    if (isMouseButtonPressed(MOUSE_BUTTON_LEFT)) {
      System.out.println("M1");
      for (Entry<Integer, Vertice> entry : grafo.vertices.entrySet()) {
        if (CollisionUtils.checkCollisionPointCircle(mousePos, entry.getValue().pos, 30)) {
          System.out.println(entry.getValue().id);
          arvoreResultante = grafo.BFS(entry.getValue());
        }
      }
    }
    if (isMouseButtonPressed(MOUSE_BUTTON_RIGHT)) {
      System.out.println("M2");
      for (Entry<Integer, Vertice> entry : grafo.vertices.entrySet()) {
        if (CollisionUtils.checkCollisionPointCircle(mousePos, entry.getValue().pos, 30)) {
          System.out.println(entry.getValue().id);
          arvoreResultante = grafo.DFS(entry.getValue());
          System.out.println(arvoreResultante);
        }
      }
    }
    
  }

  @Override
  public void draw() {
    clearBackground(WHITE);
    drawText("Botão Esq. = BFS | Botão Dir. = DFS", 10, 10, BLACK);

    grafo.draw(this);
    
        
    if (!arvoreResultante.isEmpty()) {
        for (Aresta aresta: arvoreResultante) {
            desenharSeta(aresta.origem.pos, aresta.destino.pos, 15, 15, 30, Color.decode("#eb6f92"));
        }
    }
    
    
  }

  public static void main(String[] args) {
    new SimuladorBusca();
  }
}
