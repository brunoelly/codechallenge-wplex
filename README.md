# Projeto Wplex - Sistema de Rastreamento de Eventos

Este projeto implementa um sistema para rastrear eventos gerados por rastreadores veiculares da frota da empresa "ACME". Os eventos são lidos a partir de um arquivo CSV e podem ser filtrados com base em coordenadas geográficas.

## Funcionalidades

- Leitura de eventos a partir de um arquivo CSV.
- Filtragem de eventos com base na proximidade de coordenadas (raio de 50 metros).

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Jackson (para manipulação de CSV)
- Maven (como gerenciador de dependências)

## Como Executar

1. **Clone o repositório**:

   ```bash
   git clone https://github.com/seu_usuario/wplex.git
   cd wplex
mvn clean install
mvn spring-boot:run

## Requisição Teste
GET http://localhost:8080/events?latitude=-23.70041&longitude=-046.53713

## Resultado Esperado
{
    "latitude": -23.70038,
    "longitude": -46.53697,
    "device": "3305",
    "prefix": "RUS00",
    "instant": "2018-12-10T09:58:00-02:00",
    "company": "acme",
    "distance": 16.628710542385303
  },
  {
    "latitude": -23.70032,
    "longitude": -46.53734,
    "device": "3305",
    "prefix": "RUS00",
    "instant": "2018-12-10T09:58:50-02:00",
    "company": "acme",
    "distance": 23.60763048281904
  },
  {
    "latitude": -23.70032,
    "longitude": -46.53734,
    "device": "3305",
    "prefix": "RUS00",
    "instant": "2018-12-10T09:58:50-02:00",
    "company": "acme",
    "distance": 23.60763048281904
  },
  {
    "latitude": -23.70039,
    "longitude": -46.53759,
    "device": "3170",
    "prefix": "RAX12",
    "instant": "2018-12-10T10:17:50-02:00",
    "company": "acme",
    "distance": 46.88846146432767
  },
  {
    "latitude": -23.70045,
    "longitude": -46.53705,
    "device": "3166",
    "prefix": "RUS00",
    "instant": "2018-12-10T10:19:03-02:00",
    "company": "acme",
    "distance": 9.28059246969682
  },
  {
    "latitude": -23.70038,
    "longitude": -46.5376,
    "device": "3203",
    "prefix": "RUS00",
    "instant": "2018-12-10T10:30:44-02:00",
    "company": "acme",
    "distance": 47.969990085235814
  },
  {
    "latitude": -23.70046,
    "longitude": -46.53757,
    "device": "3001",
    "prefix": "RAX12",
    "instant": "2018-12-10T10:37:06-02:00",
    "company": "acme",
    "distance": 45.143019235715705
  },
  {
    "latitude": -23.70035,
    "longitude": -46.53738,
    "device": "3203",
    "prefix": "RUS00",
    "instant": "2018-12-10T10:43:26-02:00",
    "company": "acme",
    "distance": 26.314008374031687
  },
  {
    "latitude": -23.70037,
    "longitude": -46.53761,
    "device": "3164",
    "prefix": "RAX12",
    "instant": "2018-12-10T10:54:20-02:00",
    "company": "acme",
    "distance": 49.07400824852738
  },
  {
    "latitude": -23.70044,
    "longitude": -46.53706,
    "device": "3164",
    "prefix": "RUS00",
    "instant": "2018-12-10T10:54:32-02:00",
    "company": "acme",
    "distance": 7.869207193498301
  },
  {
    "latitude": -23.70041,
    "longitude": -46.53713,
    "device": "3182",
    "prefix": "RUS00",
    "instant": "2018-12-10T10:55:40-02:00",
    "company": "acme",
    "distance": 0.0
  }
