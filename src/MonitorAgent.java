import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class MonitorAgent extends Agent {

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

                                        int nota = Integer.parseInt(
                                                        dados[2]);

                                        if (nota < 5) {

                                                System.out.println(
                                                                "\nMonitor detectou dificuldade para "
                                                                                + nome
                                                                                + " em "
                                                                                + disciplina);

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
                                        }

                                } else {

                                        block();
                                }
                        }
                });
        }
}