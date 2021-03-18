class Node{
    constructor(value){
        this.value = value
        this.left = null
        this.right = null
    }
}

class BST{
    constructor(){
        this.root = null
    }
    insert(value){
        let newNode=new Node(value)
        if(this.root==null){
            this.root=newNode
        }
        else{
            let runner=this.root;
            while(runner!=null){
                if(newNode.value>runner.value){
                    if (runner.right==null){
                        runner.right=newNode
                        return
                    }
                    else{ 
                        runner=runner.right
                    }
                }
                else if (newNode.value<runner.value){
                    if (runner.left==null){
                        runner.left=newNode
                        return
                    }
                    else{ 
                        runner=runner.left
                    }
                }
                else{
                    console.log("No duplicates")
                    return 0;
                }
            }
        }
    }

    printTree(node){
        console.log("Current node=",node.value)
        if(node.left != null){
            console.log("Left node of",node.value,"= " + node.left.value);
            this.printTree(node.left);
        }
        if(node.right != null){
            console.log("Right node of",node.value," = " + node.right.value);
            this.printTree(node.right);
        }
    }

    size(node){
        let count=1
        if(node.right!=null&&node.left!=null){
            //check if the current node has left or right
            count+=this.size(node.right)
            count+=this.size(node.left)
        }
        else if(node.right!=null){
            count+=this.size(node.right)
        }
        else if(node.left!=null){
            count+=this.size(node.left)
        }
        return count
    }

    contains(value){
        let runner=this.root
        while(runner!=null){
            if(runner.value==value){
                return true
            }
            else if (value>runner.value){
                runner=runner.right
            }
            else{
                runner=runner.left
            }
        }
        return false;
    }

    height(node=this.root){
        let leftcount=1
        let rightcount=1
        if(node.right!=null&&node.left!=null){
            //we need to compare the lenghts on the right and left
            rightcount+=this.height(node.right)
            leftcount+=this.height(node.left)
        }
        else if(node.right!=null){
            rightcount+=this.height(node.right)
        }
        else if(node.left!=null){
            leftcount+=this.height(node.left)
        }
        if(leftcount>rightcount){
            return leftcount
        }
        else{
            return rightcount
        }
    }

    isBalanced(){
        let left=this.height(this.root.left)
        let right=this.height(this.root.right)
        if(Math.abs(right-left)<=1){
            return true
        }
        else return false
    }

    remove(value){
        let runner=this.root
        let PrevNode=null
        let PrevNodeMin=null
        let direction=null
        while(runner!=null){
            if(runner.value==value){
                //When we find the number
                let rightNode=runner.right
                let leftNode=runner.left

                let minNode=runner.right

                PrevNodeMin=runner
                while (minNode.left!=null){
                    PrevNodeMin=minNode
                    minNode=minNode.left
                }
                if(minNode.right!=null){
                    PrevNodeMin.left=minNode.right
                }
                if(direction =="right"){
                    PrevNode.right=minNode
                }
                else if(direction=="left"){
                    PrevNode.left=minNode
                }
                minNode.right=rightNode
                minNode.left=leftNode
                runner.right=null
                runner.left=null
                PrevNodeMin.left=null
                break
            }
            else if (value>runner.value){
                PrevNode=runner
                runner=runner.right
                direction="right"
            }
            else{
                PrevNode=runner
                runner=runner.left
                direction="left"
            }
        }
    }
}