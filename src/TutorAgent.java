import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class TutorAgent extends Agent {

  protected void setup() {

    addBehaviour(new CyclicBehaviour() {

      @Override
      public void action() {

        ACLMessage msg = receive();

        if (msg != null) {

          String[] dados = msg.getContent()
              .split("\\|");

          String nome = dados[0];

          String recomendacao = dados[1];

          System.out.println(
              "Tutor validou conteúdo para "
                  + nome);

          ACLMessage resposta = new ACLMessage(
              ACLMessage.INFORM);

          String agenteDestino = "";

          switch (nome) {

            case "João":
              agenteDestino = "aluno0";
              break;

            case "Maria":
              agenteDestino = "aluno1";
              break;

            case "Carlos":
              agenteDestino = "aluno2";
              break;

            case "Ana":
              agenteDestino = "aluno3";
              break;
          }

          resposta.addReceiver(
              new AID(
                  agenteDestino,
                  AID.ISLOCALNAME));

          resposta.setContent(
              recomendacao);

          send(resposta);

        } else {

          block();
        }
      }
    });
  }
}