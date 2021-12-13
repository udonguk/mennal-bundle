import {makeAutoObservable, reaction} from "mobx";

export class SurveyItem {
  id = ""
  title = ""
  order
  use_yn = 'Y'
  result = ""

  isChecked = false
  status = ""

  store = null
  saveHandler = null

  constructor(store, item) {
    makeAutoObservable(this)
    this.store = store

    this.id = item.id
    this.category = item.category
    this.subCategory = item.subCategory
    this.num = item.num
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