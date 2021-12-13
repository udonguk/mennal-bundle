import {makeAutoObservable} from "mobx";

export class SurveySubCategory {
  id = ""
  code = ""
  title = ""
  reg_dt
  edit_dt
  del_dt

  store = null
  saveHandler = null

  constructor(store, item) {
    makeAutoObservable(this)
    this.store = store
    this.id = item.id
    this.code = item.code
    this.title = item.title
    this.reg_dt = item.reg_dt
    this.edit_dt = item.edit_dt
    this.del_dt = item.del_dt
  }
}