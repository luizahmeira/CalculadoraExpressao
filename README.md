# Calculadora Java

Este é um projeto simples de uma **calculadora implementada em Java**. A calculadora é capaz de extrair termos de uma expressão, converter a expressão para o formato pós-fixo (notação polonesa reversa) e calcular o resultado da expressão.

## Funcionalidades

- **Extrair termos da expressão:** Utilize o método `extrairTermos` da classe `Calculadora` para extrair os termos da expressão matemática. Este método retorna uma fila contendo os termos na ordem em que aparecem na expressão.

- **Converter para pós-fixo:** Após extrair os termos da expressão, utilize o método `gerarExprPosfixada` da classe `Calculadora` para converter a expressão para o formato pós-fixo. Este método retorna uma fila contendo os termos da expressão no formato pós-fixo.

- **Calcular o resultado:** Com a expressão no formato pós-fixo, utilize o método `calcularExprPosfixada` da classe `Calculadora` para calcular o resultado da expressão. Este método retorna o resultado da expressão matemática.

## Estrutura do projeto

O projeto é composto pelas seguintes classes:

- `Calculadora`: responsável por extrair termos da expressão, converter para pós-fixo e calcular o resultado da expressão.
- `FilaLista`: implementa uma fila utilizando uma lista encadeada.
- `PilhaLista`: implementa uma pilha utilizando uma lista encadeada.

## Utilização do projeto

Para utilizar o projeto, basta baixar ou clonar o repositório e importá-lo em uma IDE Java de sua preferência. Em seguida, compile e execute a classe `Main` para iniciar a calculadora.
