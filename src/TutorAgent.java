import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class TutorAgent extends Agent {

  protected void setup() {

    System.out.println(
        "TutorAgent iniciado!");

    addBehaviour(new CyclicBehaviour() {

      @Override
      public void action() {

        ACLMessage msg = receive();

        if (msg != null) {

          String conteudo = msg.getContent();

          System.out.println(
              "Tutor recebeu: "
                  + conteudo);

          String[] dados = conteudo.split("\\|");

          String nome = dados[0];

          String recomendacao = dados[1];

          System.out.println(
              "Conteúdo validado!");

          ACLMessage resposta = new ACLMessage(
              ACLMessage.INFORM);

          resposta.addReceiver(
              new AID(
                  "aluno1",
                  AID.ISLOCALNAME));

          resposta.addReceiver(
              new AID(
                  "aluno2",
                  AID.ISLOCALNAME));

          resposta.addReceiver(
              new AID(
                  "aluno3",
                  AID.ISLOCALNAME));

          resposta.setContent(
              "Nova recomendação para "
                  + nome
                  + ": "
                  + recomendacao);

          send(resposta);

        } else {

          block();
        }
      }
    });
  }
}