import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

public class Main {

    public static void main(String[] args) {

        try {

            Runtime rt = Runtime.instance();

            Profile p = new ProfileImpl();

            ContainerController cc = rt.createMainContainer(p);

            AgentController monitor = cc.createNewAgent(
                    "monitor",
                    "MonitorAgent",
                    null);

            AgentController recommendation = cc.createNewAgent(
                    "recommendation",
                    "RecommendationAgent",
                    null);

            AgentController tutor = cc.createNewAgent(
                    "tutor",
                    "TutorAgent",
                    null);

            AgentController aluno1 = cc.createNewAgent(
                    "aluno1",
                    "AlunoAgent",
                    new Object[] { "João" });

            AgentController aluno2 = cc.createNewAgent(
                    "aluno2",
                    "AlunoAgent",
                    new Object[] { "Maria" });

            AgentController aluno3 = cc.createNewAgent(
                    "aluno3",
                    "AlunoAgent",
                    new Object[] { "Carlos" });

            monitor.start();
            recommendation.start();
            tutor.start();

            aluno1.start();
            aluno2.start();
            aluno3.start();

            System.out.println(
                    "Sistema Multiagente iniciado!");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}