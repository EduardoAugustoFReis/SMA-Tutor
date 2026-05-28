import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class TutorAgent extends Agent {

  protected void setup() {

    System.out.println("TutorAgent iniciado!");

    addBehaviour(new CyclicBehaviour() {

      @Override
      public void action() {

        ACLMessage msg = receive();

        if (msg != null) {

          System.out.println(
              "Tutor recebeu: "
                  + msg.getContent());

          System.out.println(
              "Conteúdo validado!");

        } else {

          block();
        }
      }
    });
  }
}