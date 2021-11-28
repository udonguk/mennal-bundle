import {makeAutoObservable, reaction} from "mobx";

export class SurveyItem {
  type = ""
  id = ""
  order = ""
  title = ""
  result = ""

  isChecked = false
  status = ""

  store = null
  saveHandler = null

  constructor(store, item) {
    makeAutoObservable(this)
    this.store = store

    this.type = item.type
    this.id = item.id
    this.order = item.order
    this.title = item.title
    this.result = item.result

    this.saveHandler = reaction(
      () => this.asJson,
      json => {
        // doSomeThing
      }
    )
  }

  setStatus(param){
    this.isChecked = true
    this.status = param
  }


  delete() {

  }

  get asJson() {

  }

  dispose() {

  }
}