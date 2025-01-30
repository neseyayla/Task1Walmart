import java.util.ArrayList;

public Class PowerOfTwoHeap<T extends Comparable<T>>  {
    private final int numChildren;
    private final ArrayList<T> heapData;

    public PowerOfTwoHeap(int numChildren) {
        this.validateNumChildrem(numchildren);
        this.numChildren = numChildren;
        this.heapData = new ArrayList<T>();

    }


    private void validateNumChildren(int numChildren) {
        if(numChildren<=0) {
            throw new IllegalArgumentException("Number of children must be greater than zero");

        }
        double logValue = Math.log(numChildren) / Math.log(2);
        if (Math.ceil(logValue) != Math.floor(logValue)) {
            throw new IllegalArgumentException("Number of children must be a power of 2");

        }
    }

    public void insert(T newItem) {
        heapData.add(newItem);
        int currentIndex = heapData.size()-1;
        while (currentIndex>0){
            currentIndex = SwapUp(currentIndex);
        }

    }

    public int SwapUp(int childIndex) {
        T childValue = heapData.get(childIndex);
        int parentIndex = (int)Math.floor((float)(childIndex-1)/ numChildren);
        if(parentIndex >= 0) {
            T parentValue = heapData.get(parentIndex);
            if(childValue.compareTo(parentValue) > 0) {
                heapData.set(parentIndex,childValue);
                heapData.set(childIndex,parentValue);
                return parentIndex;
            }
        }
        return -1;
    }

    public T popMax() {
        if(heapData.isEmpty()) {
            return null;
        }

        T maxValue = heapData.get(0);
        if(heapData.size() > 1) {
            T lastItem = heapData.remove(heapData.size() -1);
            heapData.set(0,lastItem);
            int currentIndex = 0;
            while(currentIndex >= 0) {
                currentIndex = SwapDown(currentIndex);
            }
        }
        return maxValue;
    }

    public int SwapDown(int parentIndex) {
        T parentValue = heapData.get(parentIndex);
        int largestChildrenIndex = -1;
        T largestChildValue = null;

        for (int i =0; i<numChildren; i++) {
            int childIndex = numChildren*parentIndex + i +1;
            if(childIndex < heapData.size()) {
                T childValue = heapData.get(childIndex);
                if (largestChildValue == null || childValue.compareTo(largestChildValue) > 0) {
                    largestChildIndex = childIndex;
                    largestChildValue = childValue;
                }
            }
        }

        if (largestChildValue != null && parentValue.compareTo(largestChildValue) < 0) {
            heapData.set(parentIndex, largestChildValue);
            heapData.set(largestChildIndex, parentValue);
            return largestChildIndex;
        }
        return -1;
    }





}