# Laboratório Prático 2 — Strategy e SOLID

Projeto desenvolvido para a prática de refatoração utilizando o padrão de projeto **Strategy** e os princípios **SOLID**, com foco no cálculo de frete.

## Objetivo

Substituir a lógica rígida de cálculo de frete baseada em `if/else` por uma arquitetura extensível utilizando uma interface e diferentes estratégias concretas.

## Estrutura

```text
src/
├── Cliente.java
├── Item.java
├── EstrategiaFrete.java
├── FreteVipStrategy.java
├── FreteSudesteStrategy.java
├── FreteNacionalStrategy.java
└── ProcessadorDeVendas.java
```

## Estratégias implementadas

* `FreteVipStrategy` — frete de R$ 0,00
* `FreteSudesteStrategy` — frete de R$ 15,00
* `FreteNacionalStrategy` — frete de R$ 50,00

## Padrão Strategy

A interface `EstrategiaFrete` define o comportamento de cálculo:

```java
double calcularFrete(Cliente cliente);
```

O `ProcessadorDeVendas` recebe uma implementação dessa interface por meio do construtor.

Dessa forma, diferentes estratégias podem ser utilizadas sem modificar a lógica principal do processamento.

## Princípios SOLID aplicados

### SRP

Cada estratégia possui uma responsabilidade específica: calcular uma determinada modalidade de frete.

### OCP

Novas estratégias podem ser criadas sem modificar o `ProcessadorDeVendas`.

### DIP

O `ProcessadorDeVendas` depende da abstração `EstrategiaFrete`, e não de uma implementação concreta.

### ISP

A interface possui apenas o método necessário para o cálculo do frete.

### LSP

As diferentes estratégias podem ser utilizadas através do tipo `EstrategiaFrete`.

## Execução

Compilar:

```text
javac -encoding UTF-8 -d out src\*.java
```

Executar:

```text
java -cp out ProcessadorDeVendas
```

## Relatório

O relatório da arquitetura está disponível no arquivo:

```text
arquitetura.md
```
