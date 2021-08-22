import { Injectable, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {shareReplay, tap} from "rxjs/operators";


export interface User {
  id: string,
  userName: string,
  password: string,
  email: string
}

@Injectable({
  providedIn: 'root'
})
export class UserService {


  private BASE_URL:string  = "http://localhost:8080"

  private subject = new BehaviorSubject<User[]>([]);
  users$ = this.subject.asObservable();

  constructor(private  http: HttpClient) {
    this.loadAllUsers()
  }

  loadAllUsers() {
     this.http.get<User[]>(`${this.BASE_URL}/users`)
      .pipe(
        shareReplay(),
        tap(courses => this.subject.next(courses))
      ).subscribe()
  }

  deleteUserById(id: string) {
    const users = this.subject.value;

    this.subject.next(users.filter(user => user.id !== id))

    return this.http.delete(`${this.BASE_URL}/users/${id}`).subscribe()
  }
}
