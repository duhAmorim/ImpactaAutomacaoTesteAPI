#language: pt

@processo
Funcionalidade: Criação de Processos via API

  Contexto:
    Dado que tenha preechido o campo vara com valor "9999"
    E que tenha preechido o campo numero_processo com valor "10000"
    E que tenha preechido o campo natureza com valor "Atentado a Viva"
    E que tenha preechido o campo partes com valor "Terrorista X Vitima"
    E que tenha preechido o campo urgente com valor "S"
    E que tenha preechido o campo arbitramento com valor "S"
    E que tenha preechido o campo assistente_social com valor "Impacta"
    E que tenha preechido o campo data_entrada com valor "2020-06-07"
    E que tenha preechido o campo data_saida do processo com valor "2020-12-12"
    E que tenha preechido o campo data_agendamento do processo com valor "null"
    E que tenha preechido o campo status com valor "Agendado"
    E que tenha preechido o campo observacao com valor "Terrorista em prissão domiciliar"
    Quando executar a criação do novo processo

    @processoPOST
  Cenario: Criando um novo processo via API
    Então a API de proceso deve retornar o status "201"

    @processoGET
  Esquema do Cenário: Consultar um processo Criado
    Quando consultar o processo pelo "ID" da criação
    Então a API de proceso deve retornar o status "200"
    E deve está na "<campo>" "<valor>"

    Exemplos:
      | campo             | valor    |
      | vara              | 9999     |
      | assistente_social | Impacta  |
      | status            | Agendado |

  @processoPUT
  Cenario: Fazer alteração dos dados de um processo
    Quando alterar o campo urgente com valor "N"
    Então a API de proceso deve retornar o status "200"
    E deve está na urgente "N"

  @processoDELETE
  Cenario: Excluir um processo
    Quando excluir o processo
    Então a API de proceso deve retornar o status "204"
    E consular novamente o processo
    Então a API de proceso deve retornar o status "404"

