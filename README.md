# react-native-display-control

A small React Native TurboModule for controlling screen brightness and keeping the device awake, with simple functions and React hooks.

## Installation

Install from npm or yarn:

```bash
# npm
npm install react-native-display-control

# yarn
yarn add react-native-display-control
```

If you are using React Native 0.81+ with the New Architecture enabled, the TurboModule should be autolinked. Make sure you have rebuilt the native app after installation.

## Usage

### Import

```ts
import {
  activate,
  deactivate,
  setBrightness,
  resetBrightness,
  useMaxBrightness,
  useKeepAwake,
} from 'react-native-display-control';
```

### API

#### `activate(): void`

Enable keep-awake mode so that the screen does not turn off automatically.

```ts
activate();
```

#### `deactivate(): void`

Disable keep-awake mode and restore normal screen timeout behavior.

```ts
deactivate();
```

#### `setBrightness(brightness: number): void`

Set the screen brightness.

- Range: `0` (darkest) to `1` (max brightness)
- If `brightness > 1`, a console error will be logged but the call will still be forwarded to the native module.

```ts
setBrightness(0.8);
```

#### `resetBrightness(): void`

Reset the brightness back to the system default.

```ts
resetBrightness();
```

## Hooks

### `useMaxBrightness()`

React hook that sets the screen brightness to `1` when the component is mounted and resets it to the default brightness when the component is unmounted.

```ts
import React from 'react';
import { View } from 'react-native';
import { useMaxBrightness } from 'react-native-display-control';

export function ReaderScreen() {
  useMaxBrightness();

  return <View>{/* content */}</View>;
}
```

### `useKeepAwake()`

React hook that enables keep-awake mode on mount and disables it on unmount.

```ts
import React from 'react';
import { View } from 'react-native';
import { useKeepAwake } from 'react-native-display-control';

export function VideoPlayerScreen() {
  useKeepAwake();

  return <View>{/* content */}</View>;
}
```

## Example

Use both hooks together for experiences like scanning, video playback, or kiosk mode:

```ts
import React from 'react';
import { View } from 'react-native';
import { useKeepAwake, useMaxBrightness } from 'react-native-display-control';

export default function ScannerScreen() {
  useKeepAwake();
  useMaxBrightness();

  return <View>{/* scanner UI */}</View>;
}
```

## Free to use
