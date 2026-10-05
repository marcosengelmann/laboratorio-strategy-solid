# Laboratório Prático 2 — Padrão Strategy e Princípios SOLID

## 1. Aplicação do padrão Strategy e redução da Complexidade Ciclomática

Na implementação original, a classe `CalculadoraFrete` concentrava todas as regras de cálculo de frete em uma única estrutura de `if/else`. Essa abordagem aumentava a quantidade de decisões dentro do método e fazia com que cada nova regra de frete exigisse alterações na classe existente.

Com a aplicação do padrão Strategy, a lógica de cálculo foi dividida em estratégias independentes que implementam a interface `EstrategiaFrete`. Foram criadas as classes `FreteVipStrategy`, `FreteSudesteStrategy` e `FreteNacionalStrategy`.

Cada estratégia contém apenas sua própria regra de cálculo, sem estruturas condicionais para decidir qual regra deve ser utilizada. Dessa forma, a complexidade relacionada às regras de frete deixa de ficar concentrada em um único método e é distribuída entre classes menores e independentes.

A refatoração também facilita a manutenção, pois uma alteração em uma regra específica afeta somente a estratégia correspondente.

## 2. Open/Closed Principle (OCP)

A nova arquitetura respeita o princípio Aberto/Fechado porque o sistema está aberto para extensão, mas fechado para modificação.

O `ProcessadorDeVendas` depende da abstração `EstrategiaFrete`, e não de uma classe concreta de cálculo de frete. Para adicionar uma nova modalidade, como `FreteInternacionalStrategy`, seria necessário criar uma nova classe que implemente `EstrategiaFrete`.

A classe `ProcessadorDeVendas` não precisaria ser modificada para conhecer os detalhes dessa nova regra.

Portanto, novas estratégias podem ser adicionadas por extensão da arquitetura existente, sem alterar o código principal responsável pelo processamento das vendas.

## 3. Dependency Inversion Principle (DIP)

O princípio da Inversão de Dependência é aplicado porque `ProcessadorDeVendas` não depende mais diretamente da classe concreta `CalculadoraFrete`.

Em vez disso, a classe depende da abstração:

```java
private final EstrategiaFrete estrategiaFrete;
```

A estratégia concreta é fornecida externamente por meio do construtor:

```java
public ProcessadorDeVendas(EstrategiaFrete estrategiaFrete) {
    this.estrategiaFrete = estrategiaFrete;
}
```

Dessa forma, o processamento de vendas não precisa conhecer os detalhes de implementação de cada estratégia de frete.

## 4. Substituição de Liskov (LSP)

As classes `FreteVipStrategy`, `FreteSudesteStrategy` e `FreteNacionalStrategy` implementam a mesma interface e podem ser utilizadas como qualquer objeto do tipo `EstrategiaFrete`.

Isso permite que diferentes implementações sejam substituídas umas pelas outras no `ProcessadorDeVendas`, sem alterar a lógica do processamento.

## 5. Conclusão

A utilização do padrão Strategy separou as regras de cálculo de frete em classes específicas e permitiu a injeção dinâmica da estratégia no `ProcessadorDeVendas`.

Como resultado, o código apresenta menor acoplamento, responsabilidades mais bem separadas e maior facilidade para inclusão de novas regras.

Caso seja necessário implementar futuramente uma `FreteInternacionalStrategy`, a classe `ProcessadorDeVendas` não precisará ser modificada. Será necessário apenas criar a nova estratégia e injetá-la quando desejado.
