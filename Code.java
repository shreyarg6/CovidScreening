import java.util.Scanner;
import java.util.ArrayList;

/**
 * CovidScreening class allows the user go through 
 * a daily checklist of possible Covid symptoms.
 *
 * @author (Shreya Guddeti)
 */

public class CovidScreening
{
    // These instance variables have values will dynamically change 
    // based on the user’s symptoms.
    private boolean criticalSymptoms;
    private boolean highRiskSymptoms;
    private int lowRiskSymptoms;

    // The four lists hold the questions and answers to critical questions.
    private ArrayList criticalQuestionnaire = new ArrayList();
    private ArrayList criticalAnswers = new ArrayList();
    private ArrayList highRiskQuestionnaire = new ArrayList();
    private ArrayList lowRiskQuestionnaire = new ArrayList();

    /**
     * This constructor method holds the content of all the lists.
     */
    public CovidScreening()
    {
       criticalQuestionnaire.add("Exposed to covid-19 positive person?");
       criticalAnswers.add("**Stay home.Return to school only after 14 from last contact. Repeat this screening before coming to school**");
       criticalQuestionnaire.add("Covid positive?");
       criticalAnswers.add("**Stay home.Return to school only after 10 days since symptoms onset. Repeat this screening before coming to school**");
       criticalQuestionnaire.add("Had fever in the last 24 hours?");
       criticalAnswers.add("**Stay home. Return to school after 24 hrs of no fever. Repeat this screening before coming to school**");
    
       highRiskQuestionnaire.add("Do you have cough?");
       highRiskQuestionnaire.add("Do you have difficulty breathing?");
       highRiskQuestionnaire.add("Loss of taste or smell?");
       
       lowRiskQuestionnaire.add("Temperature over 100.4F?");
       lowRiskQuestionnaire.add("Congestion or runny nose?");
       lowRiskQuestionnaire.add("Nausea or vomiting?");
       lowRiskQuestionnaire.add("Sore Throat?");
       lowRiskQuestionnaire.add("Headache?");
       lowRiskQuestionnaire.add("Fatigue/muscle or body aches?");
    }
    
    /**
     * This method will iterate through all the critical questions and 
     * then it will collect the answer form the user.
     */
    private void checkCriticalSymptoms()
    {
        Scanner myObj = new Scanner(System.in);
        for(int i=0;i<criticalQuestionnaire.size();i++)
        {
            System.out.println((String)criticalQuestionnaire.get(i));
            String yesOrNo = myObj.nextLine().toLowerCase() ;  
            // If the user says ye sthis method will output a
            // specfic message per question.
            if(yesOrNo.equals("yes"))
            {
                criticalSymptoms = true;
                System.out.println(criticalAnswers.get(i));
                return;
            }
            else if(yesOrNo.equals("no"))
            {
                //do nothing
            }
            else
            {
                System.out.println("Invalid answer. Please try again Yes or No");
                i--;
            }
        }
    }
    
    /**
     * The high risk symptoms are similar to the critical method but each question
     * will have the same output.
     */
    private void checkHighRiskSymptoms()
    {
        System.out.println("------------------");
        System.out.println("High risk symptoms");
        System.out.println("------------------");
        Scanner myObj = new Scanner(System.in);
        for(int i=0;i<highRiskQuestionnaire.size();i++)
        {
            System.out.println((String)highRiskQuestionnaire.get(i));
            String yesOrNo = myObj.nextLine().toLowerCase() ;  
            // If the user says yes to one sinlge question, 
            // they will see the following output.
            if(yesOrNo.equals("yes"))
            {
                highRiskSymptoms = true;
                System.out.println("**Stay home. See doctor. Get doctors note or covid test if required**");
                return;
            }
            else if(yesOrNo.equals("no"))
            {
                //do nothing
            }
            else
            {
                System.out.println("Invalid answer. Please try again Yes or No");
                i--;
            }
        }
    }
    
    /**
     * The low risk symptoms are smilar but it has a counter 
     * instead of boolean variable.
     */
    private void checkLowRiskSymptoms()
    {
        System.out.println("-----------------");
        System.out.println("Low risk symptoms");
        System.out.println("-----------------");

        Scanner myObj = new Scanner(System.in);
        for(int i=0;i<lowRiskQuestionnaire.size();i++)
        {
            System.out.println((String)lowRiskQuestionnaire.get(i));
            String yesOrNo = myObj.nextLine().toLowerCase() ;  
            if(yesOrNo.equals("yes"))
            {
                // Here the counter goes up for every time that the the user says yes. 
                // When the user says yes to more than one the program will end.
                lowRiskSymptoms ++;
                if(lowRiskSymptoms>1) 
                {
                    System.out.println("**Stay home. See doctor. Get doctors note or covid test if required**");
                    return;
                }
            }
            else if(yesOrNo.equals("no"))
            {
                //do nothing
            }
            else
            {
                System.out.println("Invalid answer. Please try again Yes or No");
                i--;
            }
        }
        if(lowRiskSymptoms==1)
        {
            System.out.println("**Stay home**");
        }
    }
    
    /**
     * This method will call the three methods checkCriticalSymptoms, checkHighRiskSymptoms, 
     * and checkLowRiskSymptoms in that order.
     */
    public void evaluate()
    {
        System.out.println("Start Daily home covid screening");
        System.out.println("Please answer Yes or No to the following covid symptoms questionnaire :");
        System.out.println("--------------------------------------------------");
        checkCriticalSymptoms();
        // If the user has one critical symptom the program will end.
        if(criticalSymptoms) 
        {
            return;
        }
        checkHighRiskSymptoms();
        // If the program doesn't have any high risk symptoms then 
        // low risk will be checked.
        if(!highRiskSymptoms)
        {  
            checkLowRiskSymptoms();
            // If the user has no symptoms they will be prompted to got to school.
            if(lowRiskSymptoms == 0)
            {
                System.out.println("**Go school. Don't forget your face mask :)**");
            } 
        }
        System.out.println("End Daily home covid screening");
    }
    
    /**
     * This main method creates an instance of the class and fills the lists.
     */
    public static void main(String[] args)
    {
        CovidScreening homeScreen = new CovidScreening();
        homeScreen.evaluate();
    }
}
