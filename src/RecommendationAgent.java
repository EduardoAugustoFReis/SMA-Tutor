import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class RecommendationAgent extends Agent {

  protected void setup() {

    System.out.println(
        "RecommendationAgent iniciado!");

    addBehaviour(new CyclicBehaviour() {

      @Override
      public void action() {

        ACLMessage msg = receive();

        if (msg != null) {

          String conteudo = msg.getContent();

          System.out.println(
              "Recommendation recebeu: "
                  + conteudo);

          String[] dados = conteudo.split("\\|");

          String nome = dados[0];

          String disciplina = dados[1];

          ACLMessage resposta = new ACLMessage(
              ACLMessage.INFORM);

          resposta.addReceiver(
              new AID(
                  "tutor",
                  AID.ISLOCALNAME));

          resposta.setContent(
              nome
                  + "|Reforço em "
                  + disciplina);

          send(resposta);

          System.out.println(
              "Conteúdo recomendado para "
                  + nome);

        } else {

          block();
        }
      }
    });
  }
} 