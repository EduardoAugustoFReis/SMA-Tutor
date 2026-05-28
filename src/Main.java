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

            AgentController aluno = cc.createNewAgent(
                    "aluno",
                    "AlunoAgent",
                    null);

            AgentController recommendation = cc.createNewAgent(
                    "recommendation",
                    "RecommendationAgent",
                    null);

            AgentController tutor = cc.createNewAgent(
                    "tutor",
                    "TutorAgent",
                    null);

            monitor.start();

            aluno.start();

            recommendation.start();
            tutor.start();

            System.out.println(
                    "Sistema Multiagente iniciado!");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}