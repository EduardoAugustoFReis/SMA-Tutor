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

          String recomendacao = gerarRecomendacao(
              disciplina);

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
      String disciplina) {

    switch (disciplina) {

      case "Matemática":
        return "Vídeo de álgebra básica";

      case "Português":
        return "Exercícios de interpretação textual";

      case "Física":
        return "Lista de exercícios de cinemática";

      case "Química":
        return "Revisão sobre tabela periódica";

      case "História":
        return "Resumo sobre Brasil Colônia";

      default:
        return "Material de reforço";
    }
  }
}