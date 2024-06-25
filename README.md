# Projeto de Simulador NoC com Java

## Grupo: Paulo Roberto e João Victor Andrade

### Descrição do Projeto da Unidade 2

Implementar um simulador de Rede em Chip (NoC - Network on Chip) em alto nível de abstração com um algoritmo de roteamento adaptativo capaz de desviar de roteadores bloqueados na rede.

**Grupo**: No máximo dois alunos  
**Valor**: 10,0 (nota da Unidade 2)  
**Linguagem**: Java  
**Interface Gráfica**: Não precisa

### Características

- **Topologia**: MESH-2D
- **Tamanho da rede**: 8x8
- **Tamanho do flit**: Cabeçalho* + Variável inteira
    - *Cabeçalho deve conter origem e destino

### Entrada do Simulador

- Origem e destino
- Tamanho do pacote (quantas variáveis devem ser enviadas)
- Roteador(es) bloqueado(s)

### Saída do Simulador

- Rotas que os flits seguiram entre origem e destino
- Quantidade de saltos (hops) que foram necessários para chegar ao destino

### Algoritmo de Roteamento Adaptativo

- Livre de deadlock
- Livre de livelock

---

## Estrutura do Projeto

```text
src/main/java/
│
├── com.project/
│   ├── Main.java
│   └── model/
│       ├── AStar.java
│       ├── Flit.java
│       ├── No.java
│       ├── Router.java
│       └── Simulator.java
│
├── README.md

```

### Descrição das Classes

- **Main.java**: Classe principal para execução do simulador.
- **Simulator.java**: Controla a simulação, incluindo a configuração da rede, adição de flits, execução do clock e impressão do estado da rede.
- **Router.java**: Representa um roteador na rede, com informações sobre sua posição, portas e estado de bloqueio.
- **Flit.java**: Representa um flit na rede, contendo informações sobre sua origem, destino, dados e rota.
- **AStar.java**: Implementa o algoritmo A* para encontrar caminhos na rede, desviando de roteadores bloqueados.
- **No.java**: Classe auxiliar usada pelo algoritmo A* para representar nós na busca de caminhos.

---

## Como Executar

1. **Clone o repositório**:
    ```bash
    git clone <URL-do-repositório>
    cd .\src\main\java\com\project\
    ```

2. **Execute o projeto**:
    ```bash
    .    java .\Main.java
    ```
3. **[Saída esperada para bloqueados](Bloqueado.pdf)**

4. **[Saída esperada para livres](Livre.pdf)**