import comp1110.lib.Cons;
import comp1110.lib.ConsList;
import comp1110.lib.Nil;

public class GeneralTree {
    sealed interface MixedTree permits BiNode, GNode{};
    record BiNode(MixedTree left, int value, MixedTree right) implements MixedTree{};
    record GNode(ConsList<MixedTree> children) implements MixedTree{};

    int sumTree(MixedTree tree){
        return switch(tree){
            case BiNode(var left, var value,var right) ->
                value+ sumTree(left) + sumTree(right);
            case GNode(var children)->
                sumList(children);
        };
    }

    int sumList(ConsList<MixedTree> list){
        return switch(list){
            case Nil<MixedTree>() -> 0;
            case Cons<MixedTree>(var first, var rest) ->
                sumTree(first) + sumList(rest);
        };
    }
}
