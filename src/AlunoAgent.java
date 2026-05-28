import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class AlunoAgent extends Agent {

    protected void setup() {

        System.out.println("AlunoAgent iniciado!");

        addBehaviour(new OneShotBehaviour() {

            @Override
            public void action() {

                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);

                msg.addReceiver(new AID("monitor", AID.ISLOCALNAME));

                msg.setContent("Nota Matemática = 4");

                send(msg);

                System.out.println(
                        "Aluno enviou nota baixa!");
            }
        });
    }
}