\# 🚀 LogiTrack Enterprise - Sistema de Gestão de Logística



Este é um projeto prático que desenvolvi para consolidar e aplicar os conceitos mais avançados de Java que estudei ao longo do curso do Nélio Alves. 



O sistema simula o coração de uma empresa de logística: ele lê arquivos de texto (`.csv`) com vários pedidos, calcula impostos e fretes de forma flexível, gerencia uma frota na memória e gera um relatório final de fechamento usando recursos modernos da linguagem.



\---



\## 🛠️ O que eu apliquei e fixei neste projeto



Usei este desafio para sair dos exercícios clichês e treinar a lógica de sistemas reais. Aqui estão os principais tópicos que estudei e implementei:



\### 🛡️ Programação Defensiva e Exceções Customizadas

\- Criei uma exceção própria (`DomainException`) que herda de `RuntimeException`.

\- Protegi os construtores e métodos `set` de todas as entidades (`Order`, `Vehicle`, `Truck`). Eles barram na hora dados nulos, textos vazios (usando `.trim().isEmpty()`) ou números negativos, garantindo a segurança dos dados.

\- O loop que lê o arquivo ignora linhas com erros usando um bloco `try-catch` interno, exibindo o problema na tela e continuando o processamento do resto do arquivo normalmente.



\### 🔌 Interfaces e Desacoplamento (Inversão de Controle)

\- O motor principal do sistema (`ShippingProcessor`) não sabe como o imposto ou o frete são calculados. Ele conhece apenas as interfaces `TaxService` e `FreightService`.

\- Usei Injeção de Dependência pelo construtor. Se amanhã as regras de cálculo mudarem, basta criar uma nova classe e injetar no motor sem alterar uma única linha dele.

\- Implementei um `Default Method` na interface de frete para calcular um seguro obrigatório de 2% que todas as tabelas de frete herdam automaticamente.



\### ⚡ Coleções, Data-Hora e Streams API (Java Funcional)

\- Usei um `HashSet` para gerenciar a frota de veículos da empresa. Sobrescresvi os métodos `hashCode` e `equals` baseados apenas na placa do veículo para o Java impedir cadastros duplicados de forma automática na memória.

\- Guardei os pedidos finalizados com sucesso em um `ArrayList`.

\- Apliquei a API moderna de Data-Hora com `LocalDateTime` e `Instant` para gerar carimbos de auditoria local e internacional (UTC/ISO 8601).

\- Usei o poder do Java funcional com expressões lambda e pipelines de \*\*Streams\*\* (`.filter()`, `.mapToDouble()`, `.sum()`, `.sorted()`) para calcular o faturamento e ordenar a lista de prioridades sem usar loops `for` tradicionais.



\---



\## 📦 Estrutura de Pastas do Projeto



```text

├── application/

│   └── Program.java             # Ponto de entrada, leitura do lote e painel de estatísticas

├── model/

│   ├── entities/

│   │   ├── Vehicle.java         # Classe mãe abstrata dos veículos (com hashCode/equals)

│   │   ├── Truck.java           # Subclasse que calcula autonomia por eixos

│   │   ├── Airplane.java        # Subclasse que usa constante estática de autonomia

│   │   └── Order.java           # Entidade do pedido totalmente validada

│   ├── exceptions/

│   │   └── DomainException.java # Nossa exceção personalizada para erros de negócio

│   └── services/

│       ├── TaxService.java      # Interface para serviços de impostos

│       ├── FreightService.java  # Interface para fretes (com método padrão do seguro)

│       ├── BrazilTaxService.java# Cálculo concreto de imposto nacional (15%)

│       ├── StandardFreightService.java # Regras de preço de frete por região (Southeast)

│       └── ShippingProcessor.java # Motor que calcula tudo usando as interfaces

```



\---



\## 📄 Exemplo de Arquivo de Entrada (`orders\_input`)



O sistema processa arquivos com dados separados por ponto e vírgula (`;`):

```csv

101;450.0;15000.0;Southeast;ABC1234

102;-20.0;2500.0;South;XYZ5678

103;1200.0;95000.0;Northeast;ABC1234

104;50.0;3000.0;Southeast;ZZZ9999

```

\*Teste de robustez:\* O pedido `102` dispara erro pelo peso negativo. O pedido `104` falha porque a placa não existe na frota cadastrada. O sistema reporta ambos no console de forma organizada e finaliza as linhas válidas com sucesso.



