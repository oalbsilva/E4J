package br.unioeste.jgoose.util;

import com.mxgraph.util.mxDomUtils;
import java.util.Properties;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Leonardo
 */
public class IStarUtils {
    
    public static Element create(IStarElement iStarMLElement) {
        Document doc = mxDomUtils.createDocument();
        Element element = doc.createElement(iStarMLElement.getTagname());

        Properties prop = iStarMLElement.getAttributes();
        for (Object k : prop.keySet()) {
            String key = (String) k;
            element.setAttribute(key, prop.getProperty(key));
        }
        return element;
    }

    public static Element createActor() {return create(new IStarElement("actor", "Actor", "actor")); }
    public static Element createAgent() {return create(new IStarElement("actor", "Agent", "agent")); }
    public static Element createRole() {return create(new IStarElement("actor", "Role", "role")); }
    public static Element createPosition() {return create(new IStarElement("actor", "Position", "position")); }
    //
    public static Element createIS_A() {return create(new IStarElement("actorLink", "ISA", "is_a")); }
    public static Element createIS_PART_OF() {return create(new IStarElement("actorLink", "Is-part-of", "is_part_of")); }
    public static Element createINSTANCE_OF() {return create(new IStarElement("actorLink", "instance of", "instance_of")); }
    public static Element createPLAYS() {return create(new IStarElement("actorLink", "Plays", "plays")); }
    public static Element createOCCUPIES() {return create(new IStarElement("actorLink", "Occupies", "occupies")); }
    public static Element createCOVERS() {return create(new IStarElement("actorLink", "Covers", "covers")); }
    //
    public static Element createGoal() {return create(new IStarElement("ielement", "Goal", "goal")); }
    public static Element createSoftGoal() {return create(new IStarElement("ielement", "SoftGoal", "softgoal")); }
    public static Element createResource() {return create(new IStarElement("ielement", "Resource", "resource")); }
    public static Element createTask() {return create(new IStarElement("ielement", "Task", "task")); }
    //
    //TODO: add: and | or value
    public static Element createDecomposition() {return create(new IStarElement("ielementLink", "task-Decomposition", "decomposition")); }
    public static Element createMeansEnd() {return create(new IStarElement("ielementLink", "Means-end", "meansend")); }
    //TODO: add contribution values
    public static Element createContribution() {
        return create(new IStarElement("ielementLink", "Contribution", "contribution"));
    }
    public static Element createContribution(String label, String value) {
        Element result = create(new IStarElement("ielementLink", label, "contribution"));
        result.setAttribute("value", value);
        return result;
    }
    public static Element createContributionBreak(){return createContribution("Break", "break");}
    public static Element createContributionHurt(){return createContribution("Hurt","hurt");}
    public static Element createContributionSomeMinus(){return createContribution("Some -","some-");}
    public static Element createContributionUnknown(){return createContribution("Unknown","unknown");}
    public static Element createContributionSomePlus(){return createContribution("Some +","some+");}
    public static Element createContributionHelp(){return createContribution("Help","help");}
    public static Element createContributionMake(){return createContribution("Make","make");}
    public static Element createContributionAnd(){return createContribution("And","and");}
    public static Element createContributionOr(){return createContribution("Or","or");}
    //
    public static Element createDepndency() {return create(new IStarElement("dependency", "Dependency", "")); }
    
    // elements for Use Cases Diagram
    public static Element createActorUseCase() {return create(new IStarElement("actorLink", "Actor", "actor_usecase")); }
    public static Element createUseCase() {return create(new IStarElement("ielement", "Use Case", "usecase")); }
    public static Element createAssociation() {return create(new IStarElement("actorLink", "association", "association"));}
    public static Element createGeneralization() {return create(new IStarElement("actorLink", "generalization", "generalization"));}
    public static Element createInclude() {return create(new IStarElement("ielementLink", "<< include >>", "include"));}
    public static Element createExtend() {return create(new IStarElement("ielementLink", "<< extend >>", "extend"));}
}
