package br.unioeste.jgoose.istarml;

/**
 *
 * @author Leonardo Merlin - leonardo.merlin at unioeste.br
 */
public interface IStarMLConstants {

    // TAGS
    public static final String TAG_ISTARML = "istarml";
    public static final String TAG_DIAGRAM = "diagram";
    //
    public static final String TAG_ACTOR = "actor";
    public static final String TAG_ACTOR_LINK = "actorLink";
    public static final String TAG_IELEMENT = "ielement";
    public static final String TAG_IELEMENT_LINK = "ielementLink";
    //
    public static final String TAG_DEPENDENCY = "dependency";
    public static final String TAG_GRAPHIC = "graphic";
    //
    //
    // TYPE
    public static final String TYPE_ACTOR_ACTOR = "actor";
    public static final String TYPE_ACTOR_AGENT = "agent";
    public static final String TYPE_ACTOR_POSITION = "position";
    public static final String TYPE_ACTOR_ROLE = "role";
    //
    public static final String TYPE_IELEMENT_GOAL = "goal";
    public static final String TYPE_IELEMENT_RESOURCE = "resource";
    public static final String TYPE_IELEMENT_SOFTGOAL = "softgoal";
    public static final String TYPE_IELEMENT_TASK = "task";
    //
    public static final String TYPE_IELEMENT_LINK_CONTRIBUTION = "contribution";
    public static final String TYPE_IELEMENT_LINK_DECOMPOSITION = "decomposition";
    public static final String TYPE_IELEMENT_LINK_MEANSEND = "meansend";
//    public static final String TYPE_IELEMENT_LINK_DEPENDENCY = "dependency"; // it is a tag!
}
