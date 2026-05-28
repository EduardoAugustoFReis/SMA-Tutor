import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class MonitorAgent extends Agent {

    protected void setup() {

        System.out.println("MonitorAgent iniciado!");

        addBehaviour(new CyclicBehaviour() {

            @Override
            public void action() {

                ACLMessage msg = receive();

                if (msg != null) {

                    System.out.println(
                            "Monitor recebeu: "
                                    + msg.getContent());

                    if (msg.getContent().contains("4")) {

                        System.out.println(
                                "Dificuldade detectada em Matemática!");

                        ACLMessage novaMsg = new ACLMessage(ACLMessage.INFORM);

                        novaMsg.addReceiver(
                                new jade.core.AID(
                                        "recommendation",
                                        jade.core.AID.ISLOCALNAME));

                        novaMsg.setContent(
                                "Aluno precisa de reforço");

                        send(novaMsg);

                        System.out.println(
                                "Solicitação enviada ao RecommendationAgent");
                    }

                } else {

                    block();
                }
            }
        });
    }
}