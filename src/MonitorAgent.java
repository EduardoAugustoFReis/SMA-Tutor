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

                                        String[] dados = msg.getContent().split("\\|");

                                        String nome = dados[0];

                                        String disciplina = dados[1];

                                        int nota = Integer.parseInt(
                                                        dados[2]);

                                        String nivel = "";

                                        if (nota <= 3) {

                                                nivel = "GRAVE";

                                        } else if (nota <= 5) {

                                                nivel = "MODERADA";

                                        }

                                        if (!nivel.isEmpty()) {

                                                String area = obterArea(
                                                                disciplina);

                                                System.out.println(
                                                                "\nMonitor detectou dificuldade "
                                                                                + nivel
                                                                                + " para "
                                                                                + nome
                                                                                + " em "
                                                                                + disciplina
                                                                                + " ("
                                                                                + area
                                                                                + ")");

                                                ACLMessage novaMsg = new ACLMessage(
                                                                ACLMessage.INFORM);

                                                novaMsg.addReceiver(
                                                                new AID(
                                                                                "recommendation",
                                                                                AID.ISLOCALNAME));

                                                novaMsg.setContent(
                                                                nome
                                                                                + "|"
                                                                                + disciplina
                                                                                + "|"
                                                                                + nivel);

                                                send(novaMsg);
                                        }

                                } else {

                                        block();
                                }
                        }
                });
        }

        private String obterArea(
                        String disciplina) {

                switch (disciplina) {

                        case "Matemática":
                        case "Física":
                        case "Química":
                                return "Exatas";

                        default:
                                return "Humanas";
                }
        }
}