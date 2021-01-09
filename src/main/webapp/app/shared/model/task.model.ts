export interface ITask {
  id?: number;
  taskName?: string;
  taskDescription?: string;
  imageUrl?: string;
  isCompleted?: boolean;
  userId?: number;
  questionId?: number;
}

export class Task implements ITask {
  constructor(
    public id?: number,
    public taskName?: string,
    public taskDescription?: string,
    public imageUrl?: string,
    public isCompleted?: boolean,
    public userId?: number,
    public questionId?: number
  ) {
    this.isCompleted = this.isCompleted || false;
  }
}
