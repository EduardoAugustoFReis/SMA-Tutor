import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Random;

public class AlunoAgent extends Agent {

    private String nome;

    protected void setup() {

        nome = (String) getArguments()[0];

        System.out.println(
                "AlunoAgent iniciado: " + nome);

        addBehaviour(new OneShotBehaviour() {

            @Override
            public void action() {

                String[] disciplinas = {
                        "Matemática",
                        "Português",
                        "Física"
                };

                Random random = new Random();

                String disciplina = disciplinas[random.nextInt(
                        disciplinas.length)];

                int nota = random.nextInt(10);

                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);

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
                                    + " recebeu: "
                                    + msg.getContent());

                } else {

                    block();
                }
            }
        });
    }
}