# Conversor de Moedas - Programa ONE

## Descrição do Projeto

Este projeto é um **conversor de moedas** desenvolvido em Java, que permite ao usuário converter valores entre diferentes moedas utilizando taxas de câmbio atualizadas via **ExchangeRate-API**.  
O programa é executado no console e oferece um menu interativo para facilitar a escolha das moedas e inserção dos valores.

---

## Funcionalidades

- Conversão entre várias moedas:
    - USD ↔ BRL
    - EUR ↔ BRL
    - ARS → USD
    - COP → BRL
- Menu interativo no console
- Atualização automática das taxas de câmbio via API
- Possibilidade de sair do programa a qualquer momento
- Executável clicável via arquivo `.bat` no Windows

---

## Pré-requisitos

- **Java JDK 17** ou superior instalado
- **Biblioteca Gson** (gson-2.10.1.jar)
- Windows (para executar o `.bat`)

> Observação: Para outros sistemas operacionais, é necessário adaptar o comando de execução.

## Como Executar

1. **Certifique-se** de que o `gson-2.10.1.jar` e o `.bat` estão na raiz do projeto.
2. **Clique duas vezes** no arquivo `executar_conversor.bat`.
3. O console será aberto mostrando o menu interativo do conversor.
4. Siga as instruções para escolher a moeda, digitar o valor e obter a conversão.
5. Para sair, selecione a opção **7** no menu.

---

## Como Funciona

1. O programa solicita ao usuário a **opção de conversão** desejada.
2. Em seguida, o usuário digita o valor a ser convertido.
3. O programa chama a função `buscarTaxaCambio` que:
    - Conecta à API ExchangeRate-API usando **HttpClient**
    - Recebe os dados em **JSON**
    - Extrai a taxa de câmbio desejada usando **Gson**
4. O valor convertido é exibido no console.

---

## Observações

- A palavra “Opcao” no menu foi escrita sem acento para garantir compatibilidade com o console do Windows.
- Para adicionar novas moedas, basta incluir no **menu**, no **switch case**, e atualizar a lógica da função `buscarTaxaCambio`.
- O arquivo `.bat` também compila o código automaticamente caso não exista a versão compilada `.class`.