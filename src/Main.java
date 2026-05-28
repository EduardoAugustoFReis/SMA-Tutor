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

                        String[] alunos = {
                                        "João",
                                        "Maria",
                                        "Carlos",
                                        "Ana"
                        };

                        for (int i = 0; i < alunos.length; i++) {

                                AgentController aluno = cc.createNewAgent(
                                                "aluno" + i,
                                                "AlunoAgent",
                                                new Object[] { alunos[i] });

                                aluno.start();
                        }

                        monitor.start();
                        recommendation.start();
                        tutor.start();

                        System.out.println(
                                        "\nSistema Multiagente iniciado!\n");

                } catch (Exception e) {

                        e.printStackTrace();
                }
        }
}