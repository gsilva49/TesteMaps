# TesteMaps

O Teste Maps é um app que contém o seguinte fluxo:
  1. É exibido uma tela com um Mapa e um botão de "Buscar";
  2. O usuário irá adicionar um pino em uma posição desejada do mapa e apertar no botão "Buscar";
  3. Em seguida deve ser exibido uma lista com o nome das cidades;
  4. Ao ser selecionado uma cidade na lista, o aplicativo deverá abrir uma nova tela exibindo os seguintes dados:
    a. Nome da cidade escolhida
    b. Temperatura máxima (em Celsius)
    c. Temperatura mínima (em Celsius)
    d. Descrição do tempo (ex: "light rain", "overcast clouds", "Sky is Clear")
  5.  Quando o usuário voltar da tela de detalhes para a tela com a lista de cidades, será exibido um anúncio do tipo Interstitial através da 
  integração com o SDK da In Loco Media
  
Obs. Em vez de clicar no mapa para adicionar um pino, o usuário pode solicitar que o pino estaja na sua atual localização. Se isso for feito
segundos após o usuário ativar o GPS, a localização irá para 0,0 (default do GPS).

Obs2. A primeira requisição de anúncio está demorando mais do que as demais.
