import java.util.*;

// Classe que representa uma aresta com peso
class Aresta {
    int destino;
    int peso;

    public Aresta(int destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }
}

// Classe para armazenar vértice e distância na fila de prioridade
class No implements Comparable<No> {
    int vertice;
    int distancia;

    public No(int vertice, int distancia) {
        this.vertice = vertice;
        this.distancia = distancia;
    }

    @Override
    public int compareTo(No outro) {
        return Integer.compare(this.distancia, outro.distancia);
    }
}

// Classe do Grafo Ponderado
class GrafoPonderado {
    private int numVertices;
    private LinkedList<Aresta>[] adjList;

    public GrafoPonderado(int vertices) {
        this.numVertices = vertices;
        adjList = new LinkedList[vertices];

        for (int i = 0; i < vertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    // Adiciona aresta com peso
    public void addAresta(int origem, int destino, int peso) {
        adjList[origem].add(new Aresta(destino, peso));
    }

    // Adiciona aresta bidirecional
    public void addArestaBidirecional(int v1, int v2, int peso) {
        adjList[v1].add(new Aresta(v2, peso));
        adjList[v2].add(new Aresta(v1, peso));
    }

    // Algoritmo de Dijkstra
    public void dijkstra(int origem) {
        // Array de distâncias (inicializa com infinito)
        int[] distancias = new int[numVertices];
        Arrays.fill(distancias, Integer.MAX_VALUE);
        distancias[origem] = 0;

        // Array para armazenar o caminho
        int[] anterior = new int[numVertices];
        Arrays.fill(anterior, -1);

        // Array de visitados
        boolean[] visitado = new boolean[numVertices];

        // Fila de prioridade (min-heap)
        PriorityQueue<No> pq = new PriorityQueue<>();
        pq.add(new No(origem, 0));

        while (!pq.isEmpty()) {
            No atual = pq.poll();
            int v = atual.vertice;

            if (visitado[v]) continue;
            visitado[v] = true;

            // Explora vizinhos
            for (Aresta aresta : adjList[v]) {
                int viz = aresta.destino;
                int peso = aresta.peso;

                // Relaxamento
                if (!visitado[viz] && distancias[v] + peso < distancias[viz]) {
                    distancias[viz] = distancias[v] + peso;
                    anterior[viz] = v;
                    pq.add(new No(viz, distancias[viz]));
                }
            }
        }

        // Imprime resultados
        imprimirResultados(origem, distancias, anterior);
    }

    // Imprime distâncias e caminhos
    private void imprimirResultados(int origem, int[] distancias, int[] anterior) {
        System.out.println("\n DIJKSTRA - Origem: " + origem );
        System.out.println("Vértice | Distância | Caminho");
        System.out.println("--------|-----------|--------");

        for (int i = 0; i < numVertices; i++) {
            System.out.printf("   %d    |    %s    | ",
                    i,
                    distancias[i] == Integer.MAX_VALUE ? "∞" : distancias[i]);

            if (distancias[i] != Integer.MAX_VALUE) {
                imprimirCaminho(anterior, i);
            } else {
                System.out.print("Inalcançável");
            }
            System.out.println();
        }
    }

    // Imprime o caminho até um vértice
    private void imprimirCaminho(int[] anterior, int destino) {
        if (anterior[destino] == -1) {
            System.out.print(destino);
            return;
        }
        imprimirCaminho(anterior, anterior[destino]);
        System.out.print(" → " + destino);
    }

    // Imprime o grafo
    public void imprimir() {
        System.out.println("\n GRAFO PONDERADO ");
        for (int i = 0; i < numVertices; i++) {
            System.out.print(i + " → ");
            for (Aresta a : adjList[i]) {
                System.out.print("[" + a.destino + ":" + a.peso + "] ");
            }
            System.out.println();
        }
    }
}

// Classe principal com exemplos
public class Main {
    public static void main(String[] args) {
        // Exemplo 1: Grafo de cidades
        System.out.println("EXEMPLO 1: Rede de Cidades");
        GrafoPonderado cidades = new GrafoPonderado(6);

        // Adiciona rotas com distâncias (km)
        cidades.addArestaBidirecional(0, 1, 7);  // A-B: 7km
        cidades.addArestaBidirecional(0, 2, 9);  // A-C: 9km
        cidades.addArestaBidirecional(0, 5, 14); // A-F: 14km
        cidades.addArestaBidirecional(1, 2, 10); // B-C: 10km
        cidades.addArestaBidirecional(1, 3, 15); // B-D: 15km
        cidades.addArestaBidirecional(2, 3, 11); // C-D: 11km
        cidades.addArestaBidirecional(2, 5, 2);  // C-F: 2km
        cidades.addArestaBidirecional(3, 4, 6);  // D-E: 6km
        cidades.addArestaBidirecional(4, 5, 9);  // E-F: 9km

        cidades.imprimir();
        cidades.dijkstra(0); // Menor caminho a partir da cidade 0

        // Exemplo 2: Grafo direcionado
        System.out.println("\n\nEXEMPLO 2: Grafo Direcionado");
        GrafoPonderado g2 = new GrafoPonderado(5);

        g2.addAresta(0, 1, 10);
        g2.addAresta(0, 4, 5);
        g2.addAresta(1, 2, 1);
        g2.addAresta(1, 4, 2);
        g2.addAresta(2, 3, 4);
        g2.addAresta(3, 0, 7);
        g2.addAresta(3, 2, 6);
        g2.addAresta(4, 1, 3);
        g2.addAresta(4, 2, 9);
        g2.addAresta(4, 3, 2);

        g2.imprimir();
        g2.dijkstra(0);
    }
}