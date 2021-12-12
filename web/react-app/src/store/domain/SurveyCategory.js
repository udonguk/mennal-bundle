import {makeAutoObservable} from "mobx";

export class SurveyCategory {
  id = ""
  title = ""

  store = null
  saveHandler = null

  constructor(store, item) {
    makeAutoObservable(this)
    this.store = store

    this.id = item.id
    this.title = item.title
  }
}