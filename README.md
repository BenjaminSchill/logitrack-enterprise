# 🚀 LogiTrack Enterprise - Sistema de Gestão de Logística

Este é um projeto prático de encerramento de ciclo que desenvolvi para consolidar e aplicar os conceitos mais avançados de Java e Orientação a Objetos estudados ao longo do curso do Nélio Alves. 

O sistema simula um motor de logística corporativa real: ele realiza o processamento em lote de pedidos de entrega a partir de arquivos planilhados (`.csv`), gerencia restrições de uma frota ativa na memória, calcula fretes/impostos desacoplados e gera auditorias temporais automáticas.

---

## 🛠️ O que eu apliquei e fixei neste projeto

Usei este desafio para sair dos exercícios clichês de fixação e treinar a lógica de sistemas comerciais resilientes. Abaixo estão listados os pilares técnicos que implementei:

### 🛡️ Programação Defensiva e Exceções Customizadas

* **Encapsulamento Rígido:** Centralizei todas as portas de entrada das propriedades nos métodos modificadores (`set`). Os construtores delegam a inicialização para os setters, proibindo atribuições diretas desprotegidas.
* **Escudos de Validação:** Implementei testes condicionais restritivos que avaliam e barram na hora valores nulos, números zerados/negativos e textos vazios — utilizando a combinação segura `.trim().isEmpty()`.
* **Tratamento Resiliente de Erros:** Erros de digitação ou validação disparam uma exceção customizada de tempo de execução (`DomainException`). O laço de repetição captura a falha de forma isolada, loga o aviso no console e continua processando as próximas linhas do arquivo sem derrubar a aplicação.

### 🔌 Interfaces e Desacoplamento (Inversão de Controle)

* **Polimorfismo de Serviços:** O processador central de entregas (`ShippingProcessor`) está 100% isolado de regras fiscais específicas. Ele conhece e executa apenas os contratos abstratos definidos por `TaxService` e `FreightService`.
* **Injeção de Dependência:** As instâncias de cálculo reais são injetadas de forma dinâmica através do construtor do motor, garantindo escalabilidade total caso a empresa mude suas tabelas de preço no futuro.
* **Default Methods:** Utilizei recursos avançados de interface para embutir uma regra interna inviolável de seguro de carga de 2% diretamente em `FreightService`, herdada automaticamente por qualquer classe derivada.

### ⚡ Coleções Framework, Streams API e Data-Hora

* **Garantia de Unicidade (O(1)):** Configurei os métodos `hashCode` e `equals` da classe abstrata mãe `Vehicle` amarrados estritamente à placa do veículo. Isso alimenta um `HashSet` que bloqueia duplicidade de frota de forma invisível.
* **Paradigma Funcional:** Eliminei loops e contadores tradicionais no fechamento gerencial do dia. Usei a API de **Streams** encadeando expressões lambda (`.filter()`, `.mapToDouble()`, `.sum()` e `.sorted()`) para agregar faturamentos por fuso de região e listar pedidos de forma ordenada.
* **Auditoria de Tempo:** Apliquei carimbos temporais combinando o relógio local (`LocalDateTime`) com o instante absoluto internacional UTC (`Instant`), seguindo as diretrizes do padrão ISO 8601 para auditorias alfandegárias.

---

## 📦 Estrutura Arquitetural do Software

```text
├── application/
│   └── Program.java             # Ponto de entrada, loop de lote e painel analítico final
├── model/
│   ├── entities/
│   │   ├── Vehicle.java         # Superclasse abstrata da frota (hashing por placa)
│   │   ├── Truck.java           # Subclasse com autonomia calculada por número de eixos
│   │   ├── Airplane.java        # Subclasse com autonomia calculada por constante estática
│   │   └── Order.java           # Entidade de domínio do pedido totalmente blindada
│   ├── exceptions/
│   │   └── DomainException.java # Estrutura customizada de runtime exceptions da aplicação
│   └── services/
│       ├── TaxService.java      # Contrato de interface para regras fiscais de impostos
│       ├── FreightService.java  # Contrato de interface para fretes e regras de seguro
│       ├── BrazilTaxService.java# Regra concreta de imposto nacional (15%)
│       ├── StandardFreightService.java # Tabela de cálculo de frete regional por quilo
│       └── ShippingProcessor.java # Motor de processamento desacoplado orientado a contratos
```

---

## 📄 Schema do Arquivo de Entrada (`orders_input`)

Os dados inseridos na raiz do projeto devem seguir a formatação delimitada por ponto e vírgula (`;`):

```csv
101;450.0;15000.0;Southeast;ABC1234
102;-20.0;2500.0;South;XYZ5678
103;1200.0;95000.0;Northeast;ABC1234
104;50.0;3000.0;Southeast;ZZZ9999
```

* **Cenário de Teste:** O lote acima foi projetado para testar as travas do sistema. O pedido `102` falhará na hora devido ao peso negativo. O pedido `104` será rejeitado pelo escudo de frota, pois a placa `ZZZ9999` não está cadastrada. O sistema reportará ambos os logs e processará os caminhos felizes (`101` e `103`) com sucesso até o fim.
