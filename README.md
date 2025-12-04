# 🛣️ Algoritmo de Dijkstra em Java

## 📋 Descrição

Este projeto implementa o **Algoritmo de Dijkstra** para encontrar o caminho mais curto entre vértices em um grafo ponderado. A implementação utiliza uma fila de prioridade (min-heap) para otimização e suporta tanto grafos direcionados quanto não direcionados.

### Problema
Em muitas aplicações reais (sistemas de navegação GPS, roteamento de rede, logística), precisamos encontrar o caminho de menor custo entre dois pontos considerando distâncias, custos ou tempos. O problema do caminho mais curto é fundamental em ciência da computação.

### Solução
Implementação eficiente do Algoritmo de Dijkstra que:
- Encontra o menor caminho de um vértice origem para todos os outros
- Utiliza fila de prioridade para otimização (complexidade O((V+E) log V))
- Reconstrói o caminho completo, não apenas a distância
- Funciona com grafos ponderados com pesos positivos

---
## Vídeo Explicativo: https://www.youtube.com/watch?v=1peBCbqNd9w
---

## 💻 Linguagem e Versão

- **Linguagem:** Java
- **Versão Mínima:** Java 8+
- **Versão Recomendada:** Java 11+
- **Estruturas Utilizadas:** PriorityQueue, LinkedList, Arrays

## 🚀 Instruções de Execução

### Compilação

```bash
javac Main.java
```

### Execução

```bash
java Main
```

### Exemplo Completo

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/dijkstra-java.git
cd dijkstra-java

# Compile o projeto
javac Main.java

# Execute
java Main
```

## 📊 Exemplos de Entrada/Saída

### Entrada (Exemplo 1 - Rede de Cidades):

```java
GrafoPonderado cidades = new GrafoPonderado(6);
cidades.addArestaBidirecional(0, 1, 7);   // Cidade A-B: 7km
cidades.addArestaBidirecional(0, 2, 9);   // Cidade A-C: 9km
cidades.addArestaBidirecional(0, 5, 14);  // Cidade A-F: 14km
cidades.addArestaBidirecional(1, 2, 10);  // Cidade B-C: 10km
cidades.addArestaBidirecional(1, 3, 15);  // Cidade B-D: 15km
cidades.addArestaBidirecional(2, 3, 11);  // Cidade C-D: 11km
cidades.addArestaBidirecional(2, 5, 2);   // Cidade C-F: 2km
cidades.addArestaBidirecional(3, 4, 6);   // Cidade D-E: 6km
cidades.addArestaBidirecional(4, 5, 9);   // Cidade E-F: 9km

cidades.dijkstra(0); // Partindo da cidade 0
```

### Saída:

```
EXEMPLO 1: Rede de Cidades

=== GRAFO PONDERADO ===
0 → [1:7] [2:9] [5:14] 
1 → [0:7] [2:10] [3:15] 
2 → [0:9] [1:10] [3:11] [5:2] 
3 → [1:15] [2:11] [4:6] 
4 → [3:6] [5:9] 
5 → [0:14] [2:2] [4:9] 

=== DIJKSTRA - Origem: 0 ===
Vértice | Distância | Caminho
--------|-----------|--------
   0    |    0      | 0
   1    |    7      | 0 → 1
   2    |    9      | 0 → 2
   3    |   20      | 0 → 2 → 3
   4    |   20      | 0 → 2 → 5 → 4
   5    |   11      | 0 → 2 → 5
```

### Entrada (Exemplo 2 - Grafo Direcionado):

```java
GrafoPonderado g2 = new GrafoPonderado(5);
g2.addAresta(0, 1, 10);
g2.addAresta(0, 4, 5);
g2.addAresta(1, 2, 1);
g2.addAresta(4, 1, 3);
g2.addAresta(4, 2, 9);
// ... mais arestas

g2.dijkstra(0);
```

### Saída:

```
=== DIJKSTRA - Origem: 0 ===
Vértice | Distância | Caminho
--------|-----------|--------
   0    |    0      | 0
   1    |    8      | 0 → 4 → 1
   2    |    9      | 0 → 4 → 1 → 2
   3    |    7      | 0 → 4 → 3
   4    |    5      | 0 → 4
```

## ✅ Requisitos Implementados

### Requisitos Mínimos
- ✅ Estrutura de dados para grafo ponderado
- ✅ Classe Aresta com vértice destino e peso
- ✅ Implementação completa do Algoritmo de Dijkstra
- ✅ Cálculo de distâncias mínimas
- ✅ Suporte para grafos direcionados e não direcionados
- ✅ Método para adicionar arestas com pesos

### Requisitos Avançados
- ✅ Uso de fila de prioridade (PriorityQueue) para otimização
- ✅ Reconstrução completa do caminho mínimo
- ✅ Visualização formatada dos resultados
- ✅ Tratamento de vértices inalcançáveis
- ✅ Array de predecessores para rastreamento de caminhos
- ✅ Complexidade otimizada: O((V+E) log V)
- ✅ Exemplos práticos e bem documentados
- ✅ Suporte para arestas bidirecionais

## 📚 Estrutura do Código

```
📁 dijkstra-java/
├── Main.java              # Classe principal com exemplos
├── GrafoPonderado.java   # (integrado no Main.java)
├── Aresta.java           # (integrado no Main.java)
├── No.java               # (integrado no Main.java)
└── README.md             # Este arquivo
```

## 🔧 Classes e Métodos

### Classe `Aresta`
Representa uma aresta com peso
- `int destino` - vértice de destino
- `int peso` - peso/custo da aresta

### Classe `No`
Usada na fila de prioridade
- `int vertice` - identificador do vértice
- `int distancia` - distância acumulada
- `compareTo()` - comparação para min-heap

### Classe `GrafoPonderado`

| Método | Descrição |
|--------|-----------|
| `addAresta(origem, destino, peso)` | Adiciona aresta direcionada com peso |
| `addArestaBidirecional(v1, v2, peso)` | Adiciona aresta bidirecional |
| `dijkstra(origem)` | Executa o algoritmo e mostra resultados |
| `imprimir()` | Exibe o grafo com pesos |
