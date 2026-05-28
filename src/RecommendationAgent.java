import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class RecommendationAgent extends Agent {

  protected void setup() {

    System.out.println("RecommendationAgent iniciado!");

    addBehaviour(new CyclicBehaviour() {

      @Override
      public void action() {

        ACLMessage msg = receive();

        if (msg != null) {

          System.out.println(
              "Recommendation recebeu: "
                  + msg.getContent());

          ACLMessage resposta = new ACLMessage(ACLMessage.INFORM);

          resposta.addReceiver(
              new AID("tutor", AID.ISLOCALNAME));

          resposta.setContent(
              "Recomendar vídeo de álgebra");

          send(resposta);

          System.out.println(
              "Conteúdo recomendado!");

        } else {

          block();
        }
      }
    });
  }
}