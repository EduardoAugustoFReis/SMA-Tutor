import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Random;

public class AlunoAgent extends Agent {

        private String nome;

        protected void setup() {

                nome = (String) getArguments()[0];

                addBehaviour(new OneShotBehaviour() {

                        @Override
                        public void action() {

                                String[] disciplinas = {
                                                "Matemática",
                                                "Português",
                                                "Física",
                                                "Química",
                                                "História"
                                };

                                Random random = new Random();

                                String disciplina = disciplinas[random.nextInt(
                                                disciplinas.length)];

                                int nota = random.nextInt(11);

                                ACLMessage msg = new ACLMessage(
                                                ACLMessage.INFORM);

                                msg.addReceiver(
                                                new AID(
                                                                "monitor",
                                                                AID.ISLOCALNAME));

                                msg.setContent(
                                                nome
                                                                + "|"
                                                                + disciplina
                                                                + "|"
                                                                + nota);

                                send(msg);

                                System.out.println(
                                                nome
                                                                + " enviou nota "
                                                                + nota
                                                                + " em "
                                                                + disciplina);
                        }
                });

                addBehaviour(new CyclicBehaviour() {

                        @Override
                        public void action() {

                                ACLMessage msg = receive();

                                if (msg != null) {

                                        System.out.println(
                                                        nome
                                                                        + " recebeu recomendação: "
                                                                        + msg.getContent());

                                } else {

                                        block();
                                }
                        }
                });
        }
}