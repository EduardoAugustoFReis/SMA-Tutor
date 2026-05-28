import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class MonitorAgent extends Agent {

    protected void setup() {

        System.out.println(
                "MonitorAgent iniciado!");

        addBehaviour(new CyclicBehaviour() {

            @Override
            public void action() {

                ACLMessage msg = receive();

                if (msg != null) {

                    String conteudo = msg.getContent();

                    System.out.println(
                            "Monitor recebeu: "
                                    + conteudo);

                    String[] dados = conteudo.split("\\|");

                    String nome = dados[0];

                    String disciplina = dados[1];

                    int nota = Integer.parseInt(
                            dados[2]);

                    if (nota < 5) {

                        System.out.println(
                                "Dificuldade detectada para "
                                        + nome);

                        ACLMessage novaMsg = new ACLMessage(
                                ACLMessage.INFORM);

                        novaMsg.addReceiver(
                                new AID(
                                        "recommendation",
                                        AID.ISLOCALNAME));

                        novaMsg.setContent(
                                nome
                                        + "|"
                                        + disciplina);

                        send(novaMsg);

                        System.out.println(
                                "Solicitação enviada ao RecommendationAgent");

                    } else {

                        System.out.println(
                                nome
                                        + " possui desempenho satisfatório.");
                    }

                } else {

                    block();
                }
            }
        });
    }
}