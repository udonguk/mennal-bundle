import {makeAutoObservable} from "mobx";

export class TodoStore {
  authorStore
  transportLayer
  todos = []
  isLoading = true

  // constructor(transportLayer, authorStore) {
  //   makeAutoObservable(this)
  //   this.authorStore = authorStore // 작성자를 확인할 수 있는 스토어
  //   this.transportLayer = transportLayer // 서버 요청을 할 수 있는 것
  //   this.transportLayer.onReceiveTodoUpdate(updatedTodo =>
  //     this.updateTodoFromServer(updatedTodo)
  //   )
  //   this.loadTodos()
  // }
  //
  // // 서버에서 모든 todo를 가져옵니다.
  // loadTodos() {
  //   this.isLoading = true
  //   this.transportLayer.fetchTodos().then(fetchedTodos => {
  //     runInAction(() => {
  //       fetchedTodos.forEach(json => this.updateTodoFromServer(json))
  //       this.isLoading = false
  //     })
  //   })
  // }
  //
  // // 서버의 정보로 Todo를 업데이트합니다. Todo가 한 번만 존재함을 보장합니다.
  // // 새로운 Todo를 생성하거나 기존 Todo를 업데이트하거나
  // // 서버에서 삭제된 Todo를 제거할 수 있습니다.
  // updateTodoFromServer(json) {
  //   let todo = this.todos.find(todo => todo.id === json.id)
  //   if (!todo) {
  //     todo = new Todo(this, json.id)
  //     this.todos.push(todo)
  //   }
  //   if (json.isDeleted) {
  //     this.removeTodo(todo)
  //   } else {
  //     todo.updateFromJson(json)
  //   }
  // }
  //
  // // 클라이언트와 서버에 새로운 Todo를 생성합니다.
  // createTodo() {
  //   const todo = new Todo(this)
  //   this.todos.push(todo)
  //   return todo
  // }
  //
  // // Todo가 어떻게든 삭제되었을 때 클라이언트 메모리에서 삭제합니다.
  // removeTodo(todo) {
  //   this.todos.splice(this.todos.indexOf(todo), 1)
  //   todo.dispose()
  // }
}