
	public class MyQueue<E> {
		
		private java.util.LinkedList<E> list = new java.util.LinkedList<E>();
		
		public MyQueue() {
			
		}
		
		public void enqueue(E e) {
			list.addLast(e);
		}
		
		public E dequeue() {
			return list.pollFirst();
		}
		
		public String toString() {
			return list.toString();
		}
		
		public int getSize() {
			return list.size();
		}
	}