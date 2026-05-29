import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class RecommendationAgent extends Agent {

  protected void setup() {

    addBehaviour(new CyclicBehaviour() {

      @Override
      public void action() {

        ACLMessage msg = receive();

        if (msg != null) {

          String[] dados = msg.getContent()
              .split("\\|");

          String nome = dados[0];

          String disciplina = dados[1];

          String nivel = dados[2];

          String recomendacao = gerarRecomendacao(
              disciplina,
              nivel);

          System.out.println(
              "RecommendationAgent recomendou: "
                  + recomendacao);

          ACLMessage resposta = new ACLMessage(
              ACLMessage.INFORM);

          resposta.addReceiver(
              new AID(
                  "tutor",
                  AID.ISLOCALNAME));

          resposta.setContent(
              nome
                  + "|"
                  + recomendacao);

          send(resposta);

        } else {

          block();
        }
      }
    });
  }

  private String gerarRecomendacao(
      String disciplina,
      String nivel) {

    if (nivel.equals("GRAVE")) {

      switch (disciplina) {

        case "Matemática":
          return "Curso básico de álgebra";

        case "Português":
          return "Reforço completo de interpretação textual";

        case "Física":
          return "Curso introdutório de cinemática";

        case "Química":
          return "Revisão completa de tabela periódica";

        case "História":
          return "Aulas de reforço sobre Brasil Colônia";

        default:
          return "Reforço intensivo";
      }

    } else {

      switch (disciplina) {

        case "Matemática":
          return "Lista de exercícios de álgebra";

        case "Português":
          return "Exercícios de interpretação textual";

        case "Física":
          return "Lista de exercícios de cinemática";

        case "Química":
          return "Resumo sobre tabela periódica";

        case "História":
          return "Mapa mental de História";

        default:
          return "Material complementar";
      }
    }
  }
}