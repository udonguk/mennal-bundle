import { makeAutoObservable, autorun, runInAction } from "mobx"
import uuid from "node-uuid"

export class Todo {
  id = null // Todo의 고유 id, 변경할 수 없습니다.
  completed = false
  task = ""
  author = null // authorStore에서 가져온 Author 객체에 대한 참조
  store = null
  autoSave = true // Todo의 변경사항을 서버에 제출하기 위한 표시
  saveHandler = null // todo를 자동저장하는 부수효과의 Disposer(dispose).

  // constructor(store, id = uuid.v4()) {
  //   makeAutoObservable(this, {
  //     id: false,
  //     store: false,
  //     autoSave: false,
  //     saveHandler: false,
  //     dispose: false
  //   })
  //   this.store = store
  //   this.id = id
  //
  //   this.saveHandler = reaction(
  //     () => this.asJson, // JSON에서 사용되는 모든 것을 관찰합니다.
  //     json => {
  //       // autoSave가 true이면 JSON을 서버로 보냅니다.
  //       if (this.autoSave) {
  //         this.store.transportLayer.saveTodo(json)
  //       }
  //     }
  //   )
  // }
  //
  // // 클라이언트와 서버에서 해당 Todo를 제거합니다.
  // delete() {
  //   this.store.transportLayer.deleteTodo(this.id)
  //   this.store.removeTodo(this)
  // }
  //
  // get asJson() {
  //   return {
  //     id: this.id,
  //     completed: this.completed,
  //     task: this.task,
  //     authorId: this.author ? this.author.id : null
  //   }
  // }
  //
  // // 서버의 정보로 Todo를 업데이트합니다.
  // updateFromJson(json) {
  //   this.autoSave = false // 변경 사항을 서버로 다시 보내는 것을 방지합니다.
  //   this.completed = json.completed
  //   this.task = json.task
  //   this.author = this.store.authorStore.resolveAuthor(json.authorId)
  //   this.autoSave = true
  // }
  //
  // // observer를 청소합니다.
  // dispose() {
  //   this.saveHandler()
  // }
}