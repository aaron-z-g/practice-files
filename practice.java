public class solution1{
    sealed interface BinaryTree permits Leaf, Node{};
    record Leaf() implements BinaryTree{};
    record Node(BinaryTree left, int val, BinaryTree right) implements BinaryTree{};
    //count nodes
    public int countNodes(BinaryTree tree){
        if(tree == null || tree.left() ==null&& tree.right()==null){
            return 0;
        }
        int leftNodes = countNodes(tree.left());
        int rightNodes = countNodes(tree.right());

        return leftNodes+rightNodes+1;
    }

    public int sumValue(BinaryTree tree){
        if(tree == null) {
            return 0;
        }
        int sumLeft = sumValue(tree.left());
        int sumRight =sumValue(tree.right());

        return sumLeft+sumRight+tree.val();
    }

    public boolean containsNegtive(BinaryTree tree){
        return switch(tree){
            case Leaf() -> false;
            case Node(BinaryTree left, int val, BinaryTree right) ->
                val <0|| containsNegtive(left) || containsNegtive(right);
        }
    }

    public int maxDepth(BinaryTree tree){
        return switch(tree){
            case Leaf()-> 0;
            case Node(BinaryTree left, int val, BinaryTree right)->
                Math.max(maxDepth(left), maxDepth(right))+1;
        }
    }

    sealed interface ConsList<T> permits Nil, Cons {}
    record Nil<T>() implements ConsList<T> {}
    record Cons<T>(T first, ConsList<T> rest) implements ConsList<T> {}

    public ConsList<Integer> faltten(BinaryTree tree){
        return switch(tree){
            case Leaf() -> new Nil<>();
            case Node(var left, var val, var right) ->
                Append(Append(flatten(left),List.of(val)),faltten(right));
        }
    }

    public ConsList<Integer> Append(ConsList<Integer> list, ConsList<Integer> list2){
        return switch(list){
            case Nil<Integer>() -> list2;
            case Cons<Integer>(var first,var rest) ->
                new Cons<Integer>(first, Append(rest, list2));
        }
    }
}