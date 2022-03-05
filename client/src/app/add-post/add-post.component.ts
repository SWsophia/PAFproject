import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PostPayload} from "./post-payload";
import {AddPostService} from "../add-post.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css']
})
export class AddPostComponent implements OnInit {

  addPostForm!:FormGroup

  constructor(private fb:FormBuilder,
              private addpostSvc: AddPostService,
              private router: Router) { }

  ngOnInit(): void {
    this.addPostForm= this.fb.group({
      title: this.fb.control('', [Validators.required]),
      content: this.fb.control('',[Validators.required])
    })
  }

  addPost() {
    const postPayload = this.addPostForm.value as PostPayload
    console.log(">>>>current postpayload: " + postPayload)
    const content = this.addPostForm.get('content')?.value;
    const title = this.addPostForm.get('title')?.value;
    console.log("title:-- ", title)
    console.log("content:--", content)
    this.addpostSvc.addPost(postPayload).subscribe(data => {
      this.router.navigateByUrl('/')
      console.log(">>>>>payload: ",data)
    })
  }
}
