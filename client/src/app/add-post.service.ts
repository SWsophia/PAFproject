import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { PostPayload } from "./add-post/post-payload";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AddPostService {

  constructor(private httpClient: HttpClient) { }

  addPost(postPayload: PostPayload): Observable<any> {
    return this.httpClient.post('http://159.223.54.196:8080/api/posts', postPayload)
    // return this.httpClient.post('/api/posts', postPayload)
  }

  getAllPosts(): Observable<Array<PostPayload>> {
    return this.httpClient.get<Array<PostPayload>>("http://159.223.54.196:8080/api/posts/all")
    // return this.httpClient.get<Array<PostPayload>>("/api/posts/all")
  }

  getPost(parmaLink: Number): Observable<PostPayload> {
    return this.httpClient.get<PostPayload>('http://159.223.54.196:8080/api/posts/get/' + parmaLink)
    // return this.httpClient.get<PostPayload>('/api/posts/get/' + parmaLink)
  }
}


